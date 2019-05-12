package com.telusko.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.telusko.demo.domain.Address;
import com.telusko.demo.domain.UserCredentials;
import com.telusko.demo.dto.UserRegistrationDTO;
import com.telusko.demo.dto.UserResponseDTO;
import com.telusko.demo.service.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.demo.domain.User;
import com.telusko.demo.exception.ServerException;
import com.telusko.demo.repository.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
  static Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  UserRepository userRepository;

  @Autowired
  CredentialService userCredentialsService;

  @RequestMapping(path = "", method = RequestMethod.GET)
  public List<UserResponseDTO> getUsers() {
    return convertToUserResponse((List<User>) userRepository.findAll());
  }

  @RequestMapping("/{id}")
  public UserResponseDTO getUser(@PathVariable("id") long userId) {
//    return userservice.getUserResponse(userId);
    User user = userRepository.findById(userId).orElse(null);
    if (user == null) {
      logger.info("User not found, user_id = {}", userId);
      throw new ServerException("User Not Found", HttpStatus.NOT_FOUND.value());
    }
    return convertToUserResponse(user);
  }

  private UserResponseDTO convertToUserResponse(User user) {
    UserResponseDTO userResponseDTO = new UserResponseDTO(user.getName(), user.getAddress().getStreet());
    return userResponseDTO;
  }

  private List<UserResponseDTO> convertToUserResponse(List<User> users) {
    List<UserResponseDTO> userResponsDTOS = users.stream().map(user -> convertToUserResponse(user)).collect(Collectors.toList());
    return userResponsDTOS;
  }

  @PostMapping("/register")
  public UserRegistrationDTO registerUser(@RequestBody() UserRegistrationDTO newUser) {
    Address address = new Address(newUser.getStreet());
    User user = new User(newUser.getName(), address);
    String userName = newUser.getName();
    String hashedPassword = userCredentialsService.hash(newUser.getPassword());
    UserCredentials uc = new UserCredentials(userName, hashedPassword, user);
    userCredentialsService.save(uc);
    newUser.setPassword("*******");
    return newUser;
  }
}
