package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.ReviewMovie;

@Repository
public interface MovieReviewRepo extends JpaRepository<ReviewMovie, Long> {
	ReviewMovie findByUserEmailAndMovieName(String userEmail, String movieName);;

}
