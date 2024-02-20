package com.cinemania.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cinemania.exception.CinemaniaExceptionNotFound;
import com.cinemania.exception.CinemaniaForbidden;
import com.cinemania.exception.CinemaniaUnauthorized;
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
import com.cinemania.request.UserRequest;
import com.cinemania.service.UserService;
import com.cinemania.util.UserUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserUtil util;
	@Autowired
	UserRepository repository;
	@Autowired
	MovieReviewRepo movieReviewRepo;
	@Autowired
	AllTimePopularMoviesRepo allTimePopularMoviesRepo;
	@Autowired
	PopularInTheatreMoviesRepo inTheatreMoviesRepo;
	@Autowired
	TrendingMoviesRepo moviesRepo;

	@Override
	public ResponseEntity<?> addUser(UserRequest request) {
		if (repository.findByUserEmail(request.getUserEmail()) != null) {
			throw new CinemaniaForbidden("User Already Exist By This Mail");
		}
		return ResponseEntity.ok(repository.save(util.convertUserRequestIntoUserObject(request)));

	}

	@Override
	public ResponseEntity<?> getAllUsers() {
		if (repository.findAll() == null) {
			throw new CinemaniaExceptionNotFound("No User Found");
		}
		return ResponseEntity.ok(repository.findAll());
	}

	@Override
	public ResponseEntity<?> getUserByEmail(String email) {
		if (repository.findByUserEmail(email) == null) {
			throw new CinemaniaExceptionNotFound("No User Found By This mail");
		}
		return ResponseEntity.ok(repository.findByUserEmail(email));
	}

	@Override
	public ResponseEntity<?> validateUser(String email, String password) {
		if (repository.findByUserEmailAndPassword(email, password) == null) {
			throw new CinemaniaUnauthorized("Invalid user Name Or Password");
		}
		return ResponseEntity.ok(repository.findByUserEmailAndPassword(email, password));
	}

	@Override
	public ResponseEntity<?> updateUser(UserRequest cinemaniaUser) {
		if (repository.findByUserEmail(cinemaniaUser.getUserEmail()) == null) {
			throw new CinemaniaExceptionNotFound("No User Found By This mail");
		}

		if (repository.findByUserEmail(cinemaniaUser.getUserEmail()) != null) {
			CinemaniaUser cinemaniaUserNew = repository.findByUserEmail(cinemaniaUser.getUserEmail());
			cinemaniaUserNew.setPassword(cinemaniaUser.getPassword());
			cinemaniaUserNew.setUserName(cinemaniaUser.getUserName());
			repository.save(cinemaniaUserNew);
			return ResponseEntity.ok(cinemaniaUserNew);
		}
		return null;
	}

	@Override
	public ResponseEntity<?> deleteUser(String email) {
		if (repository.findByUserEmail(email) == null) {
			throw new CinemaniaExceptionNotFound("No User Found By This mail");
		} else {
			repository.deleteById(repository.findByUserEmail(email).getUserId());
			return ResponseEntity.ok("DELETED");
		}

	}

	@Override
	public ResponseEntity<?> addReview(ReviewMovie addReview) {
		String movieType = "";
		if (repository.findByUserEmail(addReview.getUserEmail()) == null) {
			return ResponseEntity.ok("User Does Not Exist");

		} else if (allTimePopularMoviesRepo.findByMovieName(addReview.getMovieName()) != null) {
			movieType = "allTimePop";
		} else if (inTheatreMoviesRepo.findByMovieName(addReview.getMovieName()) != null) {
			movieType = "inTheatre";
		} else if (moviesRepo.findByMovieName(addReview.getMovieName()) != null) {
			movieType = "trending";
		} else if (!(allTimePopularMoviesRepo.findByMovieName(addReview.getMovieName()) != null
				|| inTheatreMoviesRepo.findByMovieName(addReview.getMovieName()) != null
				|| moviesRepo.findByMovieName(addReview.getMovieName()) != null)) {
			return ResponseEntity.ok("Movie Does not Exist");
		}

		if (movieReviewRepo.findByUserEmailAndMovieName(addReview.getUserEmail(), addReview.getMovieName()) != null) {

			ReviewMovie movieReviewTest1 = movieReviewRepo.findByUserEmailAndMovieName(addReview.getUserEmail(),
					addReview.getMovieName());
			movieReviewTest1.setLocalDate(addReview.getLocalDate());
			movieReviewTest1.setRating(addReview.getRating());
			movieReviewTest1.setReview(addReview.getReview());

			if (movieType.equals("allTimePop")) {
				List<AllTimePopularMovies> list = allTimePopularMoviesRepo.findAll();
				List<ReviewMovie> movieReview = movieReviewRepo.findAll();
				for (AllTimePopularMovies newList : list) {
					Long count = 0l;
					float rating = 0;
					for (ReviewMovie mreview : movieReview) {

						if (mreview.getMovieName().equalsIgnoreCase(newList.getMovieName())) {
							System.out.println();
							count++;
							rating += mreview.getRating();
						}

					}

					try {
						float average =  (rating / count);
						newList.setMovieRating(average);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					allTimePopularMoviesRepo.save(newList);
				}
			} else if (movieType.equals("inTheatre")) {
				List<PopularInTheatreMovies> list = inTheatreMoviesRepo.findAll();
				List<ReviewMovie> movieReview = movieReviewRepo.findAll();
				for (PopularInTheatreMovies newList : list) {
					Long count = 0l;
					float rating = 0;

					for (ReviewMovie mreview : movieReview) {
						if (mreview.getMovieName().equals(newList.getMovieName())) {
							count++;
							rating += mreview.getRating();
						}
					}
					float average;
					try {
						average = (rating / count);
						newList.setMovieRating(average);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}

					inTheatreMoviesRepo.save(newList);
				}
			} else if (movieType.equals("trending")) {

				List<TrendingMovies> list = moviesRepo.findAll();
				List<ReviewMovie> movieReview = movieReviewRepo.findAll();
				for (TrendingMovies newList : list) {
					Long count = 0l;
					float rating = 0;

					for (ReviewMovie mreview : movieReview) {
						if (mreview.getMovieName().equals(newList.getMovieName())) {
							count++;
							rating += mreview.getRating();
						}
					}
					try {
						float average =  (rating / count);
						newList.setMovieRating(average);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					moviesRepo.save(newList);

				}
			}

			return ResponseEntity.ok(movieReviewTest1);
		}

		return ResponseEntity.ok(movieReviewRepo.save(addReview));
	}

}
