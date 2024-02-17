package com.bookmyshow.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Integer userId;

    @NotBlank(message = "First Name is Mandatory")
    private String firstName;

    @NotBlank(message = "Last Name is Mandatory")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Email(message = "Enter a Valid EmailId")
    @NotBlank(message = "Email is Mandatory")
    private String email;

    @NotBlank(message = "Password is Mandatory")
    private String password;

    public void setPassword(String password) {

        this.password = new BCryptPasswordEncoder().encode(password);
    }

}
