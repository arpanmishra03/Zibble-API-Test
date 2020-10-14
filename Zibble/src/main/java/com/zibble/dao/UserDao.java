package com.zibble.dao;

import com.zibble.entity.UserDetailsUD;

public interface UserDao {

    public UserDetailsUD findByMobileNumberUD(String mobileNumberUD);
    
    public void save(UserDetailsUD user);
    
}
