package com.zibble.entity;

import javax.persistence.*;

@Entity
@Table(name = "userdetailsud")
public class UserDetailsUD {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "mobilenumberud")
	private String mobileNumberUD;

	
	public UserDetailsUD() {
	}

	
	
	public UserDetailsUD(Long id, String mobileNumberUD) {
		super();
		this.id = id;
		this.mobileNumberUD = mobileNumberUD;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMobileNumberUD() {
		return mobileNumberUD;
	}



	public void setMobileNumberUD(String mobileNumberUD) {
		this.mobileNumberUD = mobileNumberUD;
	}



	@Override
	public String toString() {
		return "UserDetailsUD [id=" + id + ", mobileNumberUD=" + mobileNumberUD + "]";
	}
	
	

	

}
