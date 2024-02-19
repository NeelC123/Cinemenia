package com.cinemania.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cinemania.model.AllTimePopularMovies;
import com.cinemania.model.PopularInTheatreMovies;
import com.cinemania.model.TrendingMovies;
import com.cinemania.repository.AllTimePopularMoviesRepo;
import com.cinemania.repository.MovieReviewRepo;
import com.cinemania.repository.PopularInTheatreMoviesRepo;
import com.cinemania.repository.TrendingMoviesRepo;
import com.cinemania.request.MoviesRequest;

@Component
public class MovieUtil {
	@Autowired
	AllTimePopularMoviesRepo allTimePopularMoviesRepo;
	@Autowired
	TrendingMoviesRepo trendingMoviesRepo;
	@Autowired
	PopularInTheatreMoviesRepo popularInTheatreMoviesRepo;
	@Autowired
	MovieReviewRepo ratingRepo;

	public TrendingMovies getTrendingObject(MoviesRequest moviesRequest) {

		TrendingMovies movies = new TrendingMovies();
		movies.setMovieName(moviesRequest.getMovieName());
		movies.setMovieImgUrl(moviesRequest.getMovieImgUrl());

		return movies;
	}

	public PopularInTheatreMovies getPopularInTheatreMoviesObject(MoviesRequest moviesRequest) {

		PopularInTheatreMovies movies = new PopularInTheatreMovies();
		movies.setMovieName(moviesRequest.getMovieName());
		movies.setMovieImgUrl(moviesRequest.getMovieImgUrl());
		return movies;
	}

	public AllTimePopularMovies getAllTimePopularMoviesObject(MoviesRequest moviesRequest) {

		AllTimePopularMovies movies = new AllTimePopularMovies();
		movies.setMovieName(moviesRequest.getMovieName());
		movies.setMovieImgUrl(moviesRequest.getMovieImgUrl());
		

		return movies;
	}

}
