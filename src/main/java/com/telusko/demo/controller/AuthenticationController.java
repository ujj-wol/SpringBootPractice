package com.telusko.demo.controller;

import com.telusko.demo.domain.UserCredentials;
import com.telusko.demo.dto.SessionResponseDTO;
import com.telusko.demo.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  AuthService authService;

  @Autowired
  SessionRepository sessionRepository;

  @RequestMapping(path = "/login", method = RequestMethod.POST)
  public SessionResponseDTO login(@RequestBody UserCredentials userCredentials){
    SessionResponseDTO sessionResponseDTO = authService.login(userCredentials);
    return sessionResponseDTO;
  }

  @RequestMapping(path = "/session") // X-AUTH-TOKEN is the custom header for auth token
  public SessionResponseDTO getSession(@RequestHeader(value = "X-AUTH-TOKEN") String authToken){
    SessionResponseDTO sessionResponseDTO = authService.getSession(authToken);
    return sessionResponseDTO;
  }

}
