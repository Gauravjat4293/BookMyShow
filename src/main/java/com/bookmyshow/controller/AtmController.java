package com.bookmyshow.controller;

import com.bookmyshow.dto.AtmDto;
import com.bookmyshow.serviceimp.AtmServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AtmController {
    @Autowired
    private AtmServiceImpl AtmService;

    @PostMapping("addCardDetails")
    public ResponseEntity<AtmDto> atmCardDetails(@Valid @RequestBody AtmDto atmDto) {

        AtmDto atmDto1 = AtmService.atmCardDetails(atmDto);
        return new ResponseEntity<>(atmDto1, HttpStatus.CREATED);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("cardDetails/{atmId}")
    public ResponseEntity<AtmDto> getCardDetails(@PathVariable Integer atmId) {
        AtmDto cardDetails = AtmService.getCardDetails(atmId);
        return new ResponseEntity<>(cardDetails, HttpStatus.OK);
    }
}
