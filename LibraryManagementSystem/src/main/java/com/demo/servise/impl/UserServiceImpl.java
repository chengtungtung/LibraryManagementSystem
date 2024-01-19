package com.demo.servise.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import com.demo.servise.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	// Bcrypt 加密方式
	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userRepository;

	@Override
	public Integer register(User user) {
		// 檢查手機號碼是否註冊過
		User checkUser = userRepository.findByPhoneNumber(user.getPhoneNumber());
		if (checkUser == null) {
			// 使用 Bcrypt 加密方式
			String bcryptPassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(bcryptPassword);
			// 建立帳號
			userRepository.save(user);
			log.info("{} 已成功創建帳號", user.getPhoneNumber());
			return user.getUserId();
		} else {
			log.warn("{} 已被註冊過 ", user.getPhoneNumber());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public User findByUserId(Integer userId) {
		return userRepository.findByUserId(userId);
	}
	
	

	@Override
	public User findByPhoneNumber(String phoneNumber) {
		return userRepository.findByPhoneNumber(phoneNumber);
	}

	@Override
	public User login(User user) {
		User checkUser = userRepository.findByPhoneNumber(user.getPhoneNumber());

		// 先確認資料庫有沒有一樣的手機號碼
		if (checkUser != null) {
			// 再確認密碼有沒有一致(Bcrypt加密方式)
			if (bCryptPasswordEncoder.matches(user.getPassword(), checkUser.getPassword())) {
				return checkUser;
			} else {
				log.info("此帳號 {} 的密碼不正確", user.getPhoneNumber());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}

		} else {
			log.info("此帳號 {} 不存在", user.getPhoneNumber());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public User logout(User user) {
		userRepository.save(user);
		log.info("{} 已成功登出", user.getPhoneNumber());
		return user;
	}
	
	
	

}
