package com.cinemania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemania.request.MoviesRequest;
import com.cinemania.service.AdminService;

@RestController()
@RequestMapping("/admin")

public class AdminController {
	@Autowired
	AdminService adminService;

//1 ---------------------Add Trending Movies-----------------------------------
	@PostMapping("/addTrendingMovies")
	private ResponseEntity<?> addTrendingMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addTrendingMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Movie  Not Able to Add");

	}

//2 ------------------------Add Popular Movies In Theatre Movies-----------------
	@PostMapping("/addPopularTheatreMovies")
	private ResponseEntity<?> addPopularTheatreMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addPopularTheatreMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Movie  Not Able to Add");

	}

//3 -------------------------Add All Time Popular Movies---------------------------
	@PostMapping("/addAllTimePopularMovies")
	private ResponseEntity<?> addAllTimePopularMovies(@RequestBody MoviesRequest moviesRequest) {
		try {
			return adminService.addAllTimePopularMovies(moviesRequest);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Movie  Not Able to Add");

	}

//4 -------------------------------Get All Trending Movies---------------------------
	@GetMapping("/getAllTrendingMovies")
	private ResponseEntity<?> getAllTrendingMovies() {
		try {
			return adminService.getAllTrendingMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Able to Fetch");

	}
//5 -------------------------------Get All AllTimePopularMovies---------------------------

	@GetMapping("/getAllTimePopularMovies")
	private ResponseEntity<?> getAllTimePopularMovies() {
		try {
			return adminService.getAllTimePopularMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Able to Fetch");
	}

//6 -------------------------------Get All PopularInTheatreMovies---------------------------
	@GetMapping("/getAllPopularInTheatreMovies")
	private ResponseEntity<?> getAllPopularInTheatreMovies() {
		try {
			return adminService.getAllPopularInTheatreMovies();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Able to Fetch");

	}

//7 --------------------------------Get Movie By Name----------------------------------------
	@GetMapping("/getMovieByName/{movieName}")
	private ResponseEntity<?> getMovieByName(@PathVariable("movieName") String movieName) {
		try {
			return adminService.getMovieByName(movieName);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Able to Fetch");
	}
//8 ---------------------------------Get Movie Review By Movie Name---------------------------

	@GetMapping("/getMoviesReviewByMovieName/{movieName}")
	private ResponseEntity<?> getMoviesReviewByMoviewId(@PathVariable("movieName") String movieName) {
		try {
			return adminService.getMoviesReviewByMovieId(movieName);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Able to Fetch");
	}

}
















