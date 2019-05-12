package com.telusko.demo.dataInitialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.telusko.demo.repository.UserRepository;

@Component
public class SampleData {

	@Autowired
	private UserRepository userRepo;
	
	void createSampleData() {
		userRepo.save(UserObjectMother.createAmericanUser());
		userRepo.save(UserObjectMother.createRussianUser());
	}
}
