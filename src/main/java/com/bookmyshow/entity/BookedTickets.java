package com.bookmyshow.entity;

import com.bookmyshow.dto.BookedSeatsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookedTickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @ManyToOne
    private Users users;

    @OneToMany(mappedBy = "bookedTickets",cascade = CascadeType.ALL)
    private List<BookedSeats> bookedSeats;


    @ManyToOne
    private Movies movies;

    @ManyToOne
    private ATM atm;

    private String location;
    private String time;
    private String theaterName;
    private String food;
    private Double foodPrice;
    private Double ticketPrice;
    private Double totalPrice;


}
