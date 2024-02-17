package com.bookmyshow.services;

import com.bookmyshow.dto.BookedSeatsDto;
import com.bookmyshow.entity.BookedSeats;

import java.util.List;

public interface BookedSeatsService{

 public  List<BookedSeatsDto> getAllSeats();


}
