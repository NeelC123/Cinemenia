package com.cinemania.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MovieReviewTest1 {
	@Id
	@SequenceGenerator(name = "RatingMovies_sequence", sequenceName = "RatingMovies_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RatingMovies_sequence")
	private Long movieReviewId;
	private Long userId;
	private String movieName;
	private int rating;
	private String review;
	private LocalDate localDate;

}
