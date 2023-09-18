package com.user.service.impl;

import com.user.service.entity.Hotel;
import com.user.service.entity.Rating;
import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.external.services.HotelService;
import com.user.service.repositories.UserRepository;
import com.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {

        //generate unique userId
        String randomUserId=UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {

        //implement RATING SERVICE CALL:USING REST TEMPLATE
        List userList=userRepository.findAll();
        return userList;

    }

    @Override
    public User getUser(String userId) {

        //get user from database with the help of userRepository
        User user= userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server!! :"+userId));

        //fetch rating of the above user from RATING SERVICE
        //http://localhost:8083/ratings/users/3c946e15-9040-4c01-841e-a3417952908f

         Rating[] ratingsOfUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);

          List<Rating> ratings=Arrays.stream(ratingsOfUser).toList();

         //logger.info(String.valueOf(forObject));
        logger.info("{}",ratingsOfUser);

        List<Rating> ratingList=ratings.stream().map(rating-> {
            //api call to hotel service to get the hotel

            //http://localhost:8082/hotels/247c50be-d36a-4eb9-a419-35545ce22130

             //ResponseEntity<Hotel> forEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(),Hotel.class);
            Hotel hotel=hotelService.getHotel(rating.getHotelId());
            //Hotel hotel=forEntity.getBody();
            //logger.info("response status code:{}",forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;

        }).collect(Collectors.toList());

        user.setRatings(ratingList);
    return user;
    }
}
