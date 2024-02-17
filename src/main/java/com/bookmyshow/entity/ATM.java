package com.bookmyshow.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ATM {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer atmId;

    @Column(unique = true)
    private String cardNumber;

    private String customerName;

    private String expiryDate;


    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Integer cvvNumber;

    @OneToMany(mappedBy = "atm", cascade = CascadeType.ALL)
    private List<BookedTickets> bookedTickets;

}
