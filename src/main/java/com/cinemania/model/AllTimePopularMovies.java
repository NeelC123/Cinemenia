package com.cinemania.model;

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
public class AllTimePopularMovies {
	@Id
	@SequenceGenerator(name = "AllTimePopularMovies_sequence", sequenceName = "AllTimePopularMovies_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AllTimePopularMovies_sequence")
	private Long movieId;
	private String movieName;
	private String movieImgUrl;
	private int movieRating;
	
	
	
}
