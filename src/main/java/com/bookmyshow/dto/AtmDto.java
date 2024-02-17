package com.bookmyshow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Valid
public class AtmDto {

    private Integer atmId;

    @NotBlank(message = "ATM Number is mandatory")
    @Column(name = "ATM_Number",unique = true)
    @Size(min=16, message = "ATM Card Number Should Contain 16 Digits")
    private String cardNumber;

    @NotBlank(message = "Customer Name is Mandatory")
    @Column(name = "Customer_Name")
    private String customerName;

    private String expiryDate;

   @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
   @JsonIgnore
    private Integer cvvNumber;
}
