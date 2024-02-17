package com.bookmyshow.serviceimp;

import com.bookmyshow.Repository.MoviesRepository;
import com.bookmyshow.dto.MoviesDto;
import com.bookmyshow.entity.Movies;
import com.bookmyshow.exception.ResourceNotFoundException;
import com.bookmyshow.services.MoviesServices;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
    public class MoviesServiceImpl implements MoviesServices {

        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private MoviesRepository moviesRepository;
@Override
@Transactional
        public MoviesDto addMovies(MoviesDto moviesDto){
            Movies movies = MoviesDtoToMovies(moviesDto);
            Movies saved = moviesRepository.save(movies);
            return MoviesToMoviesDto(saved);
        }


    @Override
    public List<MoviesDto> showAllMovies() {
        List<Movies> moviesList = moviesRepository.findAll();
        List<MoviesDto> collect = moviesList.stream().
                map(movies -> MoviesToMoviesDto(movies)).collect(Collectors.toList());
        return  collect;
    }


@Override
    public MoviesDto getMovieById(Integer movieId) {
        Movies movies = moviesRepository.findById(movieId).
                orElseThrow(() -> new ResourceNotFoundException("Movie Not Found"));
       return modelMapper.map(movies,MoviesDto.class);
    }



    @Override

    public List<MoviesDto> findMovieByName(String movieName) {
        List<Movies> movieName1 = moviesRepository.findByMovieName(movieName);
        List<MoviesDto> collect = movieName1.stream().map(movies -> MoviesToMoviesDto(movies))
                .collect(Collectors.toList());
        if(collect.isEmpty()){
    throw new ResourceNotFoundException("Movie Not Found");
}
        return collect;
    }



    public List<MoviesDto> findMovieByCategory(String category){
        List<Movies> byMovieCategory = moviesRepository.findByCategory(category);
        List<MoviesDto> collect = byMovieCategory.stream().map(movies -> MoviesToMoviesDto(movies)).
                collect(Collectors.toList());
        if(collect.isEmpty()){
            throw new ResourceNotFoundException("Movie with this category not Found");
        }
        return collect;

    }



    public MoviesDto MoviesToMoviesDto(Movies movies){
        MoviesDto moviesDto = modelMapper.map(movies, MoviesDto.class);
        return  moviesDto;
    }

    public Movies MoviesDtoToMovies(MoviesDto moviesDto){
        Movies map = modelMapper.map(moviesDto, Movies.class);
        return  map;

    }




}


