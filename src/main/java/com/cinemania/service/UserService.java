package com.cinemania.service;

import java.util.List;

import com.cinemania.model.CinemaniaUser;
import com.cinemania.model.MovieReviewTest1;
import com.cinemania.request.UserRequest;

public interface UserService {

	CinemaniaUser addUser(UserRequest request);

	List<CinemaniaUser> getAllUsers();

	CinemaniaUser getUserByEmail(String email);

	CinemaniaUser validateUser(String email, String password);

	CinemaniaUser updateUser(UserRequest cinemaniaUser);

	String deleteUser(String email);

	MovieReviewTest1 addReview(MovieReviewTest1 addReview);

}
