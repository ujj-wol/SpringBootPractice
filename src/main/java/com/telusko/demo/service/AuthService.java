package com.telusko.demo.service;

import com.telusko.demo.domain.User;
import com.telusko.demo.domain.UserCredentials;
import com.telusko.demo.dto.SessionResponseDTO;
import com.telusko.demo.exception.ServerException;
import com.telusko.demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.session.MapSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

@Service
public class AuthService {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  CredentialService credentialService;

  @Autowired
  UserRepository userRepo;

  @Autowired
  SessionRepository sessionRepository;

  final String SESSION_DATA_KEY = "SESSION_DATA_KEY";

//  @Autowired
//  SessionResponseDTO sessionObject;

  public SessionResponseDTO login(UserCredentials userCredentials) {
    UserCredentials userCredentialsFromDB = credentialService.checkCredentials(userCredentials.getUserName(), userCredentials.getPassword());

    if (userCredentialsFromDB == null) {
      // throw exception to return json with 401 status code
      throw new ServerException("Invalid login credentials!", HttpStatus.UNAUTHORIZED.value());
    } else {
      // create and save sessionResponseDTO to the Session Scope
      User loggedInUser = userCredentialsFromDB.getUser();
      String name = loggedInUser.getName();
      String street = loggedInUser.getAddress().getStreet();
      String token = generateRandomBase64Token(16);

      SessionResponseDTO sessionResponseDTO = new SessionResponseDTO(token, name, street);

      // create new session holder
      MapSession sessionInMemory = (MapSession) sessionRepository.createSession();
      // set custom session id
      sessionInMemory.setId(sessionResponseDTO.getToken());
      // attach data to session holder
      sessionInMemory.setAttribute(SESSION_DATA_KEY, sessionResponseDTO);

      // save session holder to session memory
      sessionRepository.save(sessionInMemory);

      return sessionResponseDTO;
    }

  }

  public SessionResponseDTO getSession(String authToken) {
    SessionResponseDTO sessionResponseDTO = null;
    MapSession sessionInMemory = (MapSession) sessionRepository.findById(authToken);

//    //SessionResponseDTO sessionObject = (SessionResponseDTO) session.getAttribute("SessionData");
//    if (sessionObject == null) {
//      throw new ServerException("Session not found. Login first", HttpStatus.FORBIDDEN.value());
//    } else if (sessionObject.getToken().equals(token)) { //if token can be verified
//      String name = sessionObject.getName(); //get this from the specific user using the token
//      String street = sessionObject.getStreet(); //get this from the specific user using the token
//      return new SessionResponseDTO(token, name, street);
//    } else {
//      // return a 403 forbidden response which is handled through exception
//      throw new ServerException("Token from request does not match token in Server", HttpStatus.FORBIDDEN.value());
//    }

    if (sessionInMemory != null) {
      sessionResponseDTO = sessionInMemory.getAttribute(SESSION_DATA_KEY);
      sessionInMemory.setLastAccessedTime(Instant.now());
      sessionRepository.save(sessionInMemory);
      logger.info("Session found, {} :: {} :: {} ", sessionInMemory.getCreationTime(), sessionInMemory.getLastAccessedTime(), sessionInMemory.getMaxInactiveInterval());
    } else {
      logger.info("Session not found!");
      // return a 401 forbidden response which is handled through exception
      throw new ServerException("Unauthorized!", HttpStatus.UNAUTHORIZED.value());
    }
    return sessionResponseDTO;
  }

  //helper method
  public static String generateRandomBase64Token(int byteLength) {
    SecureRandom secureRandom = new SecureRandom();
    byte[] token = new byte[byteLength];
    secureRandom.nextBytes(token);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(token); //base64 encoding
  }
}


