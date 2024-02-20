package com.cinemania.service;

import org.springframework.http.ResponseEntity;

import com.cinemania.request.MoviesRequest;

public interface AdminService {

	ResponseEntity<?> addTrendingMovies(MoviesRequest moviesRequest);

	ResponseEntity<?> addPopularTheatreMovies(MoviesRequest moviesRequest);

	ResponseEntity<?> addAllTimePopularMovies(MoviesRequest moviesRequest);

	ResponseEntity<?> getMovieByName(String movieName);

	ResponseEntity<?> getAllTrendingMovies();

	ResponseEntity<?> getAllPopularInTheatreMovies();

	ResponseEntity<?> getAllTimePopularMovies();

	ResponseEntity<?> getMoviesReviewByMovieId(String movieName);

}
