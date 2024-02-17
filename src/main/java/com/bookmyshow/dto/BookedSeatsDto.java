package com.bookmyshow.dto;

import com.bookmyshow.entity.BookedSeats;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class BookedSeatsDto {

    private Integer id;

    @NotBlank(message = "Enter a Seat Number")
    private String seatNumber;

    @JsonBackReference
    private BookedTicketsDto bookedTickets;
}
