package com.bookmyshow.services;

import com.bookmyshow.dto.BookedTicketsDto;
import com.bookmyshow.entity.Users;

import java.util.List;
import java.util.Optional;

public interface BookedTicketsService {

    public BookedTicketsDto createBookingDetails(BookedTicketsDto bookedTicketsDto);

    public List<BookedTicketsDto> getBookedTicketsByEmail(String email);

}
