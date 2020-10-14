package com.zibble.service;

import com.zibble.entity.UserDetailsUD;

public interface UserService {

	public UserDetailsUD findByMobileNumberUD(String mobileNumberUD);

	public void saveUser(String mobileNumberUD);
}
