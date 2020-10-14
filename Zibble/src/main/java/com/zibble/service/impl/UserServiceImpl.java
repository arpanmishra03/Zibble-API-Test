package com.zibble.service.impl;

import com.zibble.dao.UserDao;
import com.zibble.entity.UserDetailsUD;
import com.zibble.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements UserService {

	// need to inject user dao
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public UserDetailsUD findByMobileNumberUD(String mobileNumberUD) {
		// check the database if the user already exists
		return userDao.findByMobileNumberUD(mobileNumberUD);
	}

	@Override
	public void saveUser(String mobileNumberUD) {
		UserDetailsUD user = new UserDetailsUD();
		 // assign user details to the user object
		user.setMobileNumberUD(mobileNumberUD);
		
		// save user in the database
		userDao.save(user);

		
	}

	
	
	}
