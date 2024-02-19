package com.cinemania.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinemania.model.CinemaniaUser;
import com.cinemania.model.MovieReviewTest1;
import com.cinemania.request.UserRequest;
import com.cinemania.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService service;

// 1
	@PostMapping("/addUser")
	private CinemaniaUser addUser(@RequestBody UserRequest request) {
		try {
			return service.addUser(request);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//	2
	@GetMapping("getAllUsers")
	private List<CinemaniaUser> getAllUser() {
		try {
			return service.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

// 3

	@GetMapping("getUserByEmail/{email}")
	private CinemaniaUser getUserByEmail(@PathVariable("email") String email) {
		try {
			return service.getUserByEmail(email);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//	4.

	@GetMapping("validateUser/{email}/{password}")
	private CinemaniaUser validateUser(@PathVariable("email") String email, @PathVariable("password") String password) {
		try {
			return service.validateUser(email, password);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//5.
	@PutMapping("/updateUser")

	private CinemaniaUser updateUser(@RequestBody UserRequest cinemaniaUser) {
		try {
			return service.updateUser(cinemaniaUser);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

//	6
	@DeleteMapping("/deleteUser/{email}")
	private String deleteUser(@PathVariable("email") String email) {
		try {
			return service.deleteUser(email);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "NOT DELETED";}
		
//		7
	@PostMapping("/addReview")
	private MovieReviewTest1 addReview(@RequestBody MovieReviewTest1 addReview) {
		try {
			return service.addReview(addReview);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

}