package com.bookmyshow.controller;

import com.bookmyshow.dto.TheaterDto;
import com.bookmyshow.serviceimp.TheaterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TheaterController {
    @Autowired
    public TheaterServiceImpl bookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("addTheater")
    public ResponseEntity<TheaterDto> addTheaterDetails(@RequestBody TheaterDto theaterDto){
        TheaterDto theaterDto1 = bookingService.addTheaterDetails(theaterDto);
        return new ResponseEntity<>(theaterDto1, HttpStatus.CREATED);

    }


}
