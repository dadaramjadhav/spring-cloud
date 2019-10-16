package com.example.userservice;

import java.util.UUID;

public class UserServiceImpl implements UserService{

	@Override
	public UserDto createUser(UserDto userDetails) {
		userDetails.setUserId(UUID.randomUUID().toString());
		return null;
	}

}
