package com.cinemania.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MovieReviewResponse {

	
	private String author;
	private LocalDate publishedOn;
	private String review;
	private int rating;
	
}
