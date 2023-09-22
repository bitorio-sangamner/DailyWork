package com.rating.controllers;

import com.rating.entities.Rating;
import com.rating.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingControllers {

    @Autowired
    private RatingService ratingService;

    //create Rating
    @PostMapping
    public ResponseEntity<Rating> create(@RequestBody Rating ratingObj)
    {
        Rating rating=ratingService.create(ratingObj);

        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping
    //get all
    public ResponseEntity<List<Rating>> getRatings()
    {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    //get By userId
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable String userId)
    {
        List ratingList=ratingService.getRatingByUserId(userId);

        return ResponseEntity.ok(ratingList);
    }

    //get By HotelId
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable String hotelId)
    {
        List ratingList=ratingService.getRatingByHotelId(hotelId);

        return ResponseEntity.ok(ratingList);
    }
}
