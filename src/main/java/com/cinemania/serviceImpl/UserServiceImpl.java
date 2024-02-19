package com.cinemania.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cinemania.model.CinemaniaUser;
import com.cinemania.model.MovieReviewTest1;
import com.cinemania.repository.MovieReviewRepo;
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

	@Override
	public CinemaniaUser addUser(UserRequest request) {
		return repository.save(util.convertUserRequestIntoUserObject(request));

	}

	@Override
	public List<CinemaniaUser> getAllUsers() {

		return repository.findAll();
	}

	@Override
	public CinemaniaUser getUserByEmail(String email) {

		return repository.findByUserEmail(email);
	}

	@Override
	public CinemaniaUser validateUser(String email, String password) {

		return repository.findByUserEmailAndPassword(email, password);
	}

	@Override
	public CinemaniaUser updateUser(UserRequest cinemaniaUser) {
		if (repository.findByUserEmail(cinemaniaUser.getUserEmail()) != null) {
			CinemaniaUser cinemaniaUserNew = repository.findByUserEmail(cinemaniaUser.getUserEmail());
			cinemaniaUserNew.setPassword(cinemaniaUser.getPassword());
			cinemaniaUserNew.setUserName(cinemaniaUser.getUserName());
			repository.save(cinemaniaUserNew);
			return cinemaniaUserNew;
		}
		return null;
	}

	@Override
	public String deleteUser(String email) {
		if (repository.findByUserEmail(email) != null) {
			repository.deleteById(repository.findByUserEmail(email).getUserId());
			return "DELETED";
		} else
			return "NOT DELETED";

	}

	@Override
	public MovieReviewTest1 addReview(MovieReviewTest1 addReview) {
		if (movieReviewRepo.findByUserIdAndMovieName(addReview.getUserId(), addReview.getMovieName()) != null) {

			MovieReviewTest1 movieReviewTest1 = movieReviewRepo.findByUserIdAndMovieName(addReview.getUserId(),
					addReview.getMovieName());
			movieReviewTest1.setLocalDate(addReview.getLocalDate());
			movieReviewTest1.setRating(addReview.getRating());
			movieReviewTest1.setReview(addReview.getReview());
			return movieReviewTest1;
		}
		
		return movieReviewRepo.save(addReview);
	}

}
