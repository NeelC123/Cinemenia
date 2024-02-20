package com.cinemania.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemania.model.ReviewMovie;
import com.cinemania.request.UserRequest;
import com.cinemania.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http:localhost:4200")
public class UserController {
	@Autowired
	UserService service;

// 1------------------------ Add User ------------------------------------------
	@PostMapping("/addUser")
	private ResponseEntity<?> addUser(@RequestBody UserRequest request) {
		try {
			
			return ResponseEntity.ok(service.addUser(request));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("User Not Added Email Already Exist");
	}
	

//	2 ---------------------- Get All Users ---------------------------------------
	@GetMapping("/getAllUsers")
	private ResponseEntity<?> getAllUser() {
		
		try {
			
			return ResponseEntity.ok(service.getAllUsers());
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("No User Found");
	}
	

// 3 ---------------------- Get User By Email ------------------------------------

	@GetMapping("/getUserByEmail/{email}")
	private ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		try {
			return ResponseEntity.ok(service.getUserByEmail(email));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("User Not Found");
	}

//	4 ---------------------- Validate User By Email And Password -------------------

	@GetMapping("/validateUser/{email}/{password}")
	private ResponseEntity<?> validateUser(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {
			return ResponseEntity.ok(service.validateUser(email, password));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Invalid User Name or Password");
	}

//5 ------------------------------------ Update User --------------------------------
	@PutMapping("/updateUser")

	private ResponseEntity<?> updateUser(@RequestBody UserRequest cinemaniaUser) {
		try {
			return ResponseEntity.ok(service.updateUser(cinemaniaUser));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Not Updated");
	}

//	6 ------------------------------------ Delete User --------------------------------
	@DeleteMapping("/deleteUser/{email}")
	private ResponseEntity<?> deleteUser(@PathVariable("email") String email) {
		try {
			return ResponseEntity.ok(service.deleteUser(email));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("No User Found By This mail");}

//	7 ------------------------------------ Add Review --------------------------------
	@PostMapping("/addReview")
	private ResponseEntity<?> addReview(@RequestBody ReviewMovie addReview) {
		try {
			return ResponseEntity.ok(service.addReview(addReview));
		} catch (Exception e) {
			e.printStackTrace();

		}
		return ResponseEntity.ok("Review Not Added");
	}

}