package com.cinemania.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemania.model.AllTimePopularMovies;
import com.cinemania.model.CinemaniaUser;
import com.cinemania.model.MovieReviewTest1;
import com.cinemania.model.PopularInTheatreMovies;
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
	public TrendingMovies addTrendingMovies(MoviesRequest moviesRequest) {

		if (trendingMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return trendingMoviesRepo.save(movieUtil.getTrendingObject(moviesRequest));
		else
			return null;
	}

	@Override
	public PopularInTheatreMovies addPopularTheatreMovies(MoviesRequest moviesRequest) {
		if (popularInTheatreMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return popularInTheatreMoviesRepo.save(movieUtil.getPopularInTheatreMoviesObject(moviesRequest));
		else
			return null;
	}

	@Override
	public AllTimePopularMovies addAllTimePopularMovies(MoviesRequest moviesRequest) {
		if (trendingMoviesRepo.findByMovieNameAndMovieImgUrl(moviesRequest.getMovieName(),
				moviesRequest.getMovieImgUrl()) == null)
			return allTimePopularMoviesRepo.save(movieUtil.getAllTimePopularMoviesObject(moviesRequest));
		return null;
	}

	@Override
	public MoviesResponse getMovieByName(String movieName) {
		MoviesResponse moviesResponse = new MoviesResponse();

//		AllTimePopular
		if (allTimePopularMoviesRepo.findByMovieName(movieName) != null) {
			System.out.println("all");
			AllTimePopularMovies allTimePopularMovies = allTimePopularMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(allTimePopularMovies.getMovieName());
			moviesResponse.setMovieImgUrl(allTimePopularMovies.getMovieImgUrl());
//			moviesResponse.setMovieRating(allTimePopularMovies.getMovieRating());
			return moviesResponse;
		}
//		Trending Movies
		else if (trendingMoviesRepo.findByMovieName(movieName) != null) {
			System.out.println("Trend");
			TrendingMovies trendingMovies = trendingMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(trendingMovies.getMovieName());
			moviesResponse.setMovieImgUrl(trendingMovies.getMovieImgUrl());
			moviesResponse.setMovieType("TrendingMovies");

//			moviesResponse.setMovieRating(trendingMovies.getMovieRating());
			return moviesResponse;
		}
//		Popular In Theaters 
		else if (popularInTheatreMoviesRepo.findByMovieName(movieName) != null) {
			System.out.println("popular");
			PopularInTheatreMovies popularInTheatreMovies = popularInTheatreMoviesRepo.findByMovieName(movieName);
			moviesResponse.setMovieName(popularInTheatreMovies.getMovieName());
			moviesResponse.setMovieImgUrl(popularInTheatreMovies.getMovieImgUrl());
			moviesResponse.setMovieType("PopularInTheatreMovies");
//			moviesResponse.setMovieRating(popularInTheatreMovies.getMovieRating());
			return moviesResponse;
		}
		return null;
	}

	@Override
	public List<TrendingMovies> getAllTrendingMovies() {
		List<TrendingMovies> list = trendingMoviesRepo.findAll();
		List<MovieReviewTest1> movieReview = ratingRepo.findAll();
		for (TrendingMovies newList : list) {
			Long count = 0l;
			int rating = 0;

			for (MovieReviewTest1 mreview : movieReview) {
				if (mreview.getMovieName().equals(newList.getMovieName())) {
					count++;
					rating +=mreview.getRating();
				}
			}
			try {
				int average = (int) (rating / count);
				newList.setMovieRating(average);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			trendingMoviesRepo.save(newList);
		}
		return list;

	}

	@Override
	public List<PopularInTheatreMovies> getAllPopularInTheatreMovies() {
		List<PopularInTheatreMovies> list = popularInTheatreMoviesRepo.findAll();
		List<MovieReviewTest1> movieReview = ratingRepo.findAll();
		for (PopularInTheatreMovies newList : list) {
			Long count = 0l;
			int rating = 0;

			for (MovieReviewTest1 mreview : movieReview) {
				if (mreview.getMovieName().equals(newList.getMovieName())) {
					count++;
					rating += mreview.getRating();
				}
			}
			int average;
			try {
				average = (int) (rating / count);
				newList.setMovieRating(average);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		
			popularInTheatreMoviesRepo.save(newList);
		}
		return list;
	}

	@Override
	public List<AllTimePopularMovies> getAllTimePopularMovies() {

		List<AllTimePopularMovies> list = allTimePopularMoviesRepo.findAll();
		List<MovieReviewTest1> movieReview = ratingRepo.findAll();
		for (AllTimePopularMovies newList : list) {
			Long count=0l;
			int rating = 0;
			for (MovieReviewTest1 mreview : movieReview) {
			
				if (mreview.getMovieName().equalsIgnoreCase(newList.getMovieName())) {
					System.out.println();
				  count++;	
					rating += mreview.getRating();
				}
				
				
			}
			
			try {
				int average = (int) (rating / count);
				newList.setMovieRating(average);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			allTimePopularMoviesRepo.save(newList);
		}
		return list;

	}

	@Override
	public List<MovieReviewResponse> getMoviesReviewByMovieId(String movieName) {
		List<MovieReviewTest1> list = ratingRepo.findAll();
		List<MovieReviewTest1> listOfMovieById = new ArrayList<>();
		for (MovieReviewTest1 review : list) {
			if (review.getMovieName().equals(movieName))
				listOfMovieById.add(review);
		}
		List<MovieReviewResponse> result = new ArrayList<>();
		for (MovieReviewTest1 res : listOfMovieById) {
			MovieReviewResponse movieReviewResponse = new MovieReviewResponse();
			CinemaniaUser cinemaniaUser = repository.findById(res.getUserId()).get();
			movieReviewResponse.setAuthor(cinemaniaUser.getUserName());
			movieReviewResponse.setPublishedOn(res.getLocalDate());
			movieReviewResponse.setRating(res.getRating());
			movieReviewResponse.setReview(res.getReview());
			result.add(movieReviewResponse);
		}
		return result;
	}

}
