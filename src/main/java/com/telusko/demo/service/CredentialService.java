package com.telusko.demo.service;

import com.telusko.demo.domain.UserCredentials;
import com.telusko.demo.repository.CredentialRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CredentialService {

  @Autowired
  CredentialRepository credentialRepo;

  public UserCredentials checkCredentials(String userName, String password) {
    if(credentialRepo.existsById(userName)) {
      String passwordHash = credentialRepo.findById(userName).get().getPassword();
      if(verifyHash(password, passwordHash)) {
        return credentialRepo.findById(userName).get();
      }
    }
    return null;
  }

  public String hash(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt());
  }

  public boolean verifyHash(String password, String hash) {
    return BCrypt.checkpw(password, hash);
  }

  public UserCredentials save(UserCredentials uc) {
    return credentialRepo.save(uc);
  }

}
