package com.bookmyshow.Repository;

import com.bookmyshow.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    List<Movies> findByMovieName(String movieName);


    List<Movies> findByCategory(String category);
}
