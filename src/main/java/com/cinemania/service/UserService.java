package com.cinemania.service;

import org.springframework.http.ResponseEntity;

import com.cinemania.model.ReviewMovie;
import com.cinemania.request.UserRequest;

public interface UserService {

	ResponseEntity<?> addUser(UserRequest request);

	ResponseEntity<?> getAllUsers();

	ResponseEntity<?> getUserByEmail(String email);

	ResponseEntity<?> validateUser(String email, String password);

	ResponseEntity<?> updateUser(UserRequest cinemaniaUser);

	ResponseEntity<?> deleteUser(String email);

	ResponseEntity<?> addReview(ReviewMovie addReview);

}
