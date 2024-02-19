package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.PopularInTheatreMovies;

@Repository
public interface PopularInTheatreMoviesRepo extends JpaRepository<PopularInTheatreMovies, Long> {
	PopularInTheatreMovies findByMovieName(String movieName);

	PopularInTheatreMovies findByMovieNameAndMovieImgUrl(String movieName, String movieImgUrl);
}
