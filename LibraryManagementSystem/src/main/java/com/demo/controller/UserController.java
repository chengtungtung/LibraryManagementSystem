package com.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.User;
import com.demo.servise.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public String index() {
		System.out.println("test");
		return "spring boot";
	}

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user) {
		Date now = new Date();
		user.setRegistrationTime(now);
		Integer userId = userService.register(user);
		User registeredUser = userService.findByUserId(userId);
		return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user,Model model) {
		User successLoginUser = userService.login(user);
		model.addAttribute("LoginOK", successLoginUser);
		return ResponseEntity.status(HttpStatus.OK).body(successLoginUser);
	}
	
	@PutMapping("/logout")
	public ResponseEntity<User> logout(@RequestBody User user){
		User result = userService.findByPhoneNumber(user.getPhoneNumber());
		Date now = new Date();
		result.setLastLoginTime(now);
		User successLogoutUser = userService.logout(result);
		
		return ResponseEntity.status(HttpStatus.OK).body(successLogoutUser);
	}
	
}
