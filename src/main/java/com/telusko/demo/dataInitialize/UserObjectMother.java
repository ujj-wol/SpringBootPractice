package com.telusko.demo.dataInitialize;

import com.telusko.demo.domain.Address;
import com.telusko.demo.domain.User;

public class UserObjectMother {

	//Could use builder pattern to create user inside these methods below.
	
	public static User createAmericanUser() {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName("Obama");
		Address add = new Address();
		add.setStreet("White House");
		user.setAddress(add);
		
		return user;
	}
	
	
	public static User createRussianUser() {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName("Putin");
		Address add = new Address();
		add.setStreet("Russia");
		user.setAddress(add);
		
		return user;
	}
	

}
