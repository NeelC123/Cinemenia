package com.cinemania.service;

import java.util.List;

import com.cinemania.model.AllTimePopularMovies;
import com.cinemania.model.PopularInTheatreMovies;
import com.cinemania.model.TrendingMovies;
import com.cinemania.request.MoviesRequest;
import com.cinemania.response.MovieReviewResponse;
import com.cinemania.response.MoviesResponse;

public interface AdminService {

	TrendingMovies addTrendingMovies(MoviesRequest moviesRequest);

	PopularInTheatreMovies addPopularTheatreMovies(MoviesRequest moviesRequest);

	AllTimePopularMovies addAllTimePopularMovies(MoviesRequest moviesRequest);

	MoviesResponse getMovieByName(String movieName);

	List<TrendingMovies> getAllTrendingMovies();

	List<PopularInTheatreMovies> getAllPopularInTheatreMovies();

	List<AllTimePopularMovies> getAllTimePopularMovies();

	List<MovieReviewResponse> getMoviesReviewByMovieId(String movieName);

}
