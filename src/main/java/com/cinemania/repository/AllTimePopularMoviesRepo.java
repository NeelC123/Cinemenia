package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.AllTimePopularMovies;


@Repository
public interface AllTimePopularMoviesRepo extends JpaRepository<AllTimePopularMovies, Long> {
	AllTimePopularMovies findByMovieName(String movieName);
	AllTimePopularMovies  findByMovieNameAndMovieImgUrl(String movieName, String movieImgUrl);
}
