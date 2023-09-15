package com.hotel.controllers;

import com.hotel.entities.Hotel;
import com.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    //create
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        Hotel hotelObj=hotelService.create(hotel);

        return ResponseEntity.status(HttpStatus.CREATED).body(hotelObj);
    }

    //get single hotel
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId)
    {
        Hotel hotelObj=hotelService.get(hotelId);

        return ResponseEntity.ok(hotelObj);
    }

    //get all hotel
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll()
    {
        List hotels=hotelService.getAll();

       return ResponseEntity.ok(hotels);
    }


}
