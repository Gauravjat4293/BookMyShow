package com.bookmyshow.dto;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class BookedTicketsDto {

    private Integer ticketId;
    private UserDto users;
    private MoviesDto movies;
    private AtmDto atm;
    private String location;
    private String time;
    private String theaterName;
    private String food;
    private Double foodPrice;
    private Double ticketPrice;
    private Double totalPrice;
    @JsonManagedReference
    private List<BookedSeatsDto> bookedSeats;

}
