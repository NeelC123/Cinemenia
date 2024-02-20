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
public class TrendingMovies {
	@Id
	@SequenceGenerator(name = "TrendingMovies_sequence", sequenceName = "TrendingMovies_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TrendingMovies_sequence")
	private Long movieId;
	private String movieName;
	private String movieImgUrl;
	private float movieRating;

}
