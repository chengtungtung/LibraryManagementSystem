package com.demo.servise;

import com.demo.model.User;

public interface UserService {

	Integer register(User user);
	User findByUserId(Integer userId);
	User findByPhoneNumber(String phoneNumber);
	User login(User user);
	User logout(User user);
}
