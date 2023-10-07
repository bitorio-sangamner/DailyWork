package com.deliveryboy.controller;

import com.deliveryboy.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/update")
    public ResponseEntity<?> updateLocation(String s)
    {
        for(int i=0;i<100;i++) {
            String coordinates = ("( " + Math.round(Math.random() * 100) + "," + Math.round(Math.random() * 100) + " " + ")");

            kafkaService.updateLocation(coordinates);
        }
            return new ResponseEntity<>(Map.of("message", "Location updated"), HttpStatus.OK);


    }
}
