package com.bookmyshow.services;

import com.bookmyshow.dto.MoviesDto;

import java.util.List;

public interface MoviesServices {

   MoviesDto addMovies(MoviesDto moviesDto);

     List<MoviesDto> showAllMovies();

    MoviesDto getMovieById(Integer movieId);

     List<MoviesDto> findMovieByName(String movieName);
     List<MoviesDto> findMovieByCategory(String category);


}
