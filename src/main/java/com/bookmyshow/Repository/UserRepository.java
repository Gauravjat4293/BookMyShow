package com.bookmyshow.Repository;

import com.bookmyshow.dto.BookedTicketsDto;
import com.bookmyshow.entity.BookedTickets;
import com.bookmyshow.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String email);

}