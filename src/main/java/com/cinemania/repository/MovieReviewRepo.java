package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.MovieReviewTest1;


@Repository
public interface MovieReviewRepo extends JpaRepository<MovieReviewTest1, Long> {
	MovieReviewTest1 findByUserIdAndMovieName(Long userId, String movieName);

}
