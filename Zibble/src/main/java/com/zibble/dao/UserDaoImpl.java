package com.zibble.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.zibble.entity.UserDetailsUD;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public UserDetailsUD findByMobileNumberUD(String mobileNumberUD) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<UserDetailsUD> theQuery = currentSession.createQuery("from UserDetailsUD where mobileNumberUD=:number", UserDetailsUD.class);
		theQuery.setParameter("number", mobileNumberUD);
		
		UserDetailsUD theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(UserDetailsUD theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);
	}

}
