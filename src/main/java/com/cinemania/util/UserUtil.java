package com.cinemania.util;

import org.springframework.stereotype.Component;

import com.cinemania.model.CinemaniaUser;
import com.cinemania.request.UserRequest;
@Component
public class UserUtil {

	public CinemaniaUser convertUserRequestIntoUserObject(UserRequest request) {
		CinemaniaUser cinemaniaUser = new CinemaniaUser();
		cinemaniaUser.setUserName(request.getUserName());
		cinemaniaUser.setUserEmail(request.getUserEmail());
		cinemaniaUser.setPassword(request.getPassword());

		return cinemaniaUser;

	}
}
