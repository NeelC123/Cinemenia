package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.TrendingMovies;

@Repository
public interface TrendingMoviesRepo extends JpaRepository<TrendingMovies, Long> {
	TrendingMovies findByMovieName(String movieName);

	TrendingMovies findByMovieNameAndMovieImgUrl(String movieName, String movieImgUrl);
}
