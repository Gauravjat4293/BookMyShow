package com.bookmyshow.Repository;

import com.bookmyshow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,String> {

}
