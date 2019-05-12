package com.telusko.demo.dataInitialize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
//@Slf4j
public class AppInit implements ApplicationRunner {
	
	@Autowired
	private SampleData sampleData;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
//		log.info("Loading sample data into DB...");
		sampleData.createSampleData();
	}

}

//This above is another way to initialize data without using data.sql