package com.cinemania.response;

import lombok.Data;

@Data
public class MoviesResponse {
	
	private String movieName;
	private String movieImgUrl;
	private int movieRating;
	private String movieType;
}
