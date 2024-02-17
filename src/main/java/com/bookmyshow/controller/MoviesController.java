package com.bookmyshow.controller;

import com.bookmyshow.dto.MoviesDto;
import com.bookmyshow.serviceimp.MoviesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class MoviesController {
    @Autowired
    private MoviesServiceImpl moviesService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("movies/addMovies")
    public ResponseEntity<MoviesDto> addMovies(@RequestBody MoviesDto moviesDto) {
        MoviesDto moviesDto1 = moviesService.addMovies(moviesDto);
        return new ResponseEntity<>(moviesDto1, HttpStatus.CREATED);

    }

    @GetMapping("showAllMovies")
    public ResponseEntity<List<MoviesDto>> showAllMovies() {
        List<MoviesDto> moviesDto = moviesService.showAllMovies();
        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }


    @GetMapping("getMovie/{movieId}")
    public ResponseEntity<MoviesDto> getMovieById(@PathVariable Integer movieId) {
        MoviesDto movieById = moviesService.getMovieById(movieId);
        return new ResponseEntity<>(movieById, HttpStatus.OK);

    }

    @GetMapping("movies/{movieName}")
    public ResponseEntity<List<MoviesDto>> findMovieByName(@PathVariable String movieName) {
        List<MoviesDto> movieByName = moviesService.findMovieByName(movieName);
        return new ResponseEntity<>(movieByName, HttpStatus.OK);

    }

    @GetMapping("/movies/category/{category}")
    public ResponseEntity<List<MoviesDto>> findMovieByCategory(@PathVariable String category){
        List<MoviesDto> movieByCategory = moviesService.findMovieByCategory(category);
        return  new ResponseEntity<>(movieByCategory, HttpStatus.OK);
    }


}
