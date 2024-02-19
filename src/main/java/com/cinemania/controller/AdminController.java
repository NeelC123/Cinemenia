package com.cinemania.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemania.model.AllTimePopularMovies;
import com.cinemania.model.PopularInTheatreMovies;
import com.cinemania.model.TrendingMovies;
import com.cinemania.request.MoviesRequest;
import com.cinemania.response.MovieReviewResponse;
import com.cinemania.response.MoviesResponse;
import com.cinemania.service.AdminService;

@RestController()
@RequestMapping("/admin")

public class AdminController {
	@Autowired
	AdminService adminService;

//1 ---------------------Add Trending Movies-----------------------------------
	@PostMapping("/addTrendingMovies")
	private TrendingMovies addTrendingMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addTrendingMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

//2 ------------------------Add Popular Movies In Theatre Movies-----------------
	@PostMapping("/addPopularTheatreMovies")
	private PopularInTheatreMovies addPopularTheatreMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addPopularTheatreMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

//3 -------------------------Add All Time Popular Movies---------------------------
	@PostMapping("/addAllTimePopularMovies")
	private AllTimePopularMovies addAllTimePopularMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addAllTimePopularMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

//4 -------------------------------------------------------------------------------
	@GetMapping("/getAllTrendingMovies")
	private List<TrendingMovies> getAllTrendingMovies() {
		try {
			return adminService.getAllTrendingMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}
//	5

	@GetMapping("/getAllTimePopularMovies")
	private List<AllTimePopularMovies> getAllTimePopularMovies() {
		try {
			return adminService.getAllTimePopularMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//6 -------------------------------------------------------------------------------
	@GetMapping("/getAllPopularInTheatreMovies")
	private List<PopularInTheatreMovies> getAllPopularInTheatreMovies() {
		try {
			return adminService.getAllPopularInTheatreMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

//7 -------------------------------------------------------------------------------
	@GetMapping("/getMovieByName/{movieName}")
	private MoviesResponse getMovieByName(@PathVariable("movieName") String movieName) {
		try {
			return adminService.getMovieByName(movieName);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}
//9 -------------------------------------------------------------------------------

	@GetMapping("/getMoviesReviewByMovieId/{movieName}")
	private List<MovieReviewResponse> getMoviesReviewByMoviewId(@PathVariable("movieName") String movieName) {
		try {
			return adminService.getMoviesReviewByMovieId(movieName);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

}
