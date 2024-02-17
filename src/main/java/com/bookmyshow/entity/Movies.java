package com.bookmyshow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer movieId;

    private String moviePoster;

    private String movieName;

    private String releaseDate;

    private String category;

    private String genre;

    private float rating;

    private String country;

    @OneToMany(mappedBy = "movies",cascade = CascadeType.ALL)
    private List<BookedTickets> bookedTickets;


}
