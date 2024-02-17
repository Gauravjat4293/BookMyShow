package com.bookmyshow.controller;

import com.bookmyshow.Repository.BookedTicketsRepository;
import com.bookmyshow.dto.BookedTicketsDto;

import com.bookmyshow.serviceimp.BookedTicketsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
      public class BookedTicketsController {

    @Autowired
    private BookedTicketsServicesImpl bookedTicketsService;


    @GetMapping("bookedTicketDetails")
    public ResponseEntity<BookedTicketsDto> createTicket(@RequestBody BookedTicketsDto  bookedTicketsDto){
        BookedTicketsDto bookingDetails = bookedTicketsService.createBookingDetails(bookedTicketsDto);
        return new ResponseEntity<>(bookingDetails, HttpStatus.CREATED);
    }
    @GetMapping("BookedTickets/{email}")
    public ResponseEntity<List<BookedTicketsDto>> getBookedTicketsByEmail(@PathVariable String email){
        List<BookedTicketsDto> bookingByEmail = bookedTicketsService.getBookedTicketsByEmail(email);
        return new ResponseEntity<>(bookingByEmail,HttpStatus.OK);
    }


}
