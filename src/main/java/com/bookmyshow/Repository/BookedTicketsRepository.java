package com.bookmyshow.Repository;

import com.bookmyshow.entity.BookedTickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedTicketsRepository extends JpaRepository<BookedTickets,Integer> {
    List<BookedTickets> findByTheaterNameAndLocationAndTime(String theaterName, String location, String time);

//    List<Booking> findByTheaterNameAndLocationAndTime(String theaterName, String location, String time);
}

