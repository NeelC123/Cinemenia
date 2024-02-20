package com.cinemania.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cinemania.exception.CinemaniaExceptionNotFound;
import com.cinemania.model.AllTimePopularMovies;
import com.cinemania.model.CinemaniaUser;
import com.cinemania.model.PopularInTheatreMovies;
import com.cinemania.model.ReviewMovie;
import com.cinemania.model.TrendingMovies;
import com.cinemania.repository.AllTimePopularMoviesRepo;
import com.cinemania.repository.MovieReviewRepo;
import com.cinemania.repository.PopularInTheatreMoviesRepo;
import com.cinemania.repository.TrendingMoviesRepo;
import com.cinemania.repository.UserRepository;
import com.cinemania.request.MoviesRequest;
import com.cinemania.response.MovieReviewResponse;
import com.cinemania.response.MoviesResponse;
import com.cinemania.service.AdminService;
import com.cinemania.util.MovieUtil;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	MovieUtil movieUtil;

	@Autowired
	AllTimePopularMoviesRepo allTimePopularMoviesRepo;
	@Autowired
	TrendingMoviesRepo trendingMoviesRepo;
	@Autowired
	PopularInTheatreMoviesRepo popularInTheatreMoviesRepo;
	@Autowired
	MovieReviewRepo ratingRepo;

	@Autowired
	UserRepository repository;

	@Override
	public ResponseEntity<?> addTrendingMovies(MoviesRequest moviesRequest) {

		if (trendingMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return ResponseEntity.ok(trendingMoviesRepo.save(movieUtil.getTrendingObject(moviesRequest)));
		else {
			return ResponseEntity.ok("Already Exist");
		}
	}

	@Override
	public ResponseEntity<?> addPopularTheatreMovies(MoviesRequest moviesRequest) {
		if (popularInTheatreMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return ResponseEntity
					.ok(popularInTheatreMoviesRepo.save(movieUtil.getPopularInTheatreMoviesObject(moviesRequest)));
		else
			return ResponseEntity.ok("Already Exist");
	}

	@Override
	public ResponseEntity<?> addAllTimePopularMovies(MoviesRequest moviesRequest) {
		if (trendingMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return ResponseEntity
					.ok(allTimePopularMoviesRepo.save(movieUtil.getAllTimePopularMoviesObject(moviesRequest)));
		return ResponseEntity.ok("Already Exist");
	}

	@Override
	public ResponseEntity<?> getMovieByName(String movieName) {
		MoviesResponse moviesResponse = new MoviesResponse();

//		AllTimePopular
		if (allTimePopularMoviesRepo.findByMovieName(movieName) != null) {
			AllTimePopularMovies allTimePopularMovies = allTimePopularMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(allTimePopularMovies.getMovieName());
			moviesResponse.setMovieImgUrl(allTimePopularMovies.getMovieImgUrl());
//			moviesResponse.setMovieRating(allTimePopularMovies.getMovieRating());
			return ResponseEntity.ok(moviesResponse);
		}
//		Trending Movies
		else if (trendingMoviesRepo.findByMovieName(movieName) != null) {
			TrendingMovies trendingMovies = trendingMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(trendingMovies.getMovieName());
			moviesResponse.setMovieImgUrl(trendingMovies.getMovieImgUrl());
			moviesResponse.setMovieType("TrendingMovies");

//			moviesResponse.setMovieRating(trendingMovies.getMovieRating());
			return ResponseEntity.ok(moviesResponse);
		}
//		Popular In Theaters 
		else if (popularInTheatreMoviesRepo.findByMovieName(movieName) != null) {
			PopularInTheatreMovies popularInTheatreMovies = popularInTheatreMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(popularInTheatreMovies.getMovieName());
			moviesResponse.setMovieImgUrl(popularInTheatreMovies.getMovieImgUrl());
			moviesResponse.setMovieType("PopularInTheatreMovies");
//			moviesResponse.setMovieRating(popularInTheatreMovies.getMovieRating());
			return ResponseEntity.ok(moviesResponse);
		} else {
			throw new CinemaniaExceptionNotFound("Movie Not Found");
		}
//		return ResponseEntity.ok("Not Existed");
	}

	@Override
	public ResponseEntity<?> getAllTrendingMovies() {
		if (trendingMoviesRepo.findAll() == null) {
			return ResponseEntity.ok("No Movies Found");
		}
		List<TrendingMovies> list = trendingMoviesRepo.findAll();

		return ResponseEntity.ok(list);

	}

	@Override
	public ResponseEntity<?> getAllPopularInTheatreMovies() {
		if (popularInTheatreMoviesRepo.findAll() == null) {
			return ResponseEntity.ok("No Movies Found");
		}
		List<PopularInTheatreMovies> list = popularInTheatreMoviesRepo.findAll();

		return ResponseEntity.ok(list);
	}

	@Override
	public ResponseEntity<?> getAllTimePopularMovies() {
		if (allTimePopularMoviesRepo.findAll() == null) {
			return ResponseEntity.ok("No Movies Found");
		}
		List<AllTimePopularMovies> list = allTimePopularMoviesRepo.findAll();

		return ResponseEntity.ok(list);

	}

	@Override
	public ResponseEntity<?> getMoviesReviewByMovieId(String movieName) {
		List<ReviewMovie> list = ratingRepo.findAll();
		List<ReviewMovie> listOfMovieById = new ArrayList<>();
		for (ReviewMovie review : list) {
			if (review.getMovieName().equals(movieName))
				listOfMovieById.add(review);
		}
		List<MovieReviewResponse> result = new ArrayList<>();
		for (ReviewMovie res : listOfMovieById) {
			MovieReviewResponse movieReviewResponse = new MovieReviewResponse();
			CinemaniaUser cinemaniaUser = repository.findByUserEmail(res.getUserEmail());
			movieReviewResponse.setAuthor(cinemaniaUser.getUserName());
			movieReviewResponse.setPublishedOn(res.getLocalDate());
			movieReviewResponse.setRating(res.getRating());
			movieReviewResponse.setReview(res.getReview());
			result.add(movieReviewResponse);
		}
		return ResponseEntity.ok(result);
	}

}
