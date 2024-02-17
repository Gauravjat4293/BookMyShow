package com.bookmyshow.Repository;

import com.bookmyshow.entity.BookedSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedSeatsRepository extends JpaRepository<BookedSeats,Integer> {

}
