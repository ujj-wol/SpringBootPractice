package com.telusko.demo;

import javax.annotation.PostConstruct;

import com.telusko.demo.domain.Address;
import com.telusko.demo.domain.UserCredentials;
import com.telusko.demo.repository.CredentialRepository;
import com.telusko.demo.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.telusko.demo.domain.User;
import com.telusko.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import java.util.HashMap;

@SpringBootApplication
public class SpringBootProjectPracticeUserProfileApplication {

	@Autowired
	UserRepository userRepo;

//	@Autowired
//	CredentialService credentialService;  //looks like it does not inherit all methods from CrudRepository

	@Autowired
  CredentialRepository credentialRepo;

  @Autowired
  CredentialService credentialService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectPracticeUserProfileApplication.class, args);
	}

	/**
	 * Pre-load the system with users and addresses. This is another way
	 */
	@PostConstruct
	public void init() {
    String password = "password";
		String hashedPassword = credentialService.hash(password);
		User newUser = new User("Gandalf", new Address("HimalKoDada"));
		credentialRepo.save(new UserCredentials("admin", hashedPassword, newUser));

		password = "pass1";
		hashedPassword = credentialService.hash(password);
		newUser = new User("Frodo", new Address("HobbitLand"));
		credentialRepo.save(new UserCredentials("user1", hashedPassword, newUser));

    password = "pass2";
    hashedPassword = credentialService.hash(password);
    newUser = new User("Potato", new Address("BarikoPata"));
    credentialRepo.save(new UserCredentials("user2", hashedPassword, newUser));
	}

	// initializing SessionRepository bean as MapSessionRepository
	@Bean
  public SessionRepository sessionRepository(){
	  MapSessionRepository sessionRepository = new MapSessionRepository(new HashMap<>());
	  sessionRepository.setDefaultMaxInactiveInterval(60);
	  return sessionRepository;
  }

}



/* For errors
 * 
 * { 
 * 		status: 404, 
 * 		message: "User not found" 
 * }
 
1. In application.properties add: debug = true
   with @Value annotation over a field inside our ErrorController implementation class, the debug
   value will be injected by Spring

2. Autowire an Interface called ErrorAttributes in a field(errorAttributes) inside this class

3. Define a method error whose response is ErrorJson which is a model class we defined with fields (status, error, message, timestamp, trace)
   and a constructor with arguments (int status, Map<String, Object> errorAttributes).
   Spring will take care of giving appropriate HTTP response code
   
   
1. Jackson JSR310 converters take care of converting Java8 date and time classes to JSON 
   representation using the @JsonFormat annotation
   <dependency>
   <groupId>com.fasterxml.jackson.datatype</groupId>
   <artifactId>jackson-datatype-jsr310</artifactId>
	</dependency>
	
2.	Define a class to represent API errors (APIError class) with enough fields to hold relevant
	information about errors that happen during rest calls.
	   private HttpStatus status;
	   @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	   private LocalDateTime timestamp;
	   private String message;
	   private String debugMessage;
	   private List<ApiSubError> subErrors;
	
*/

//Do Token based authentication for user


