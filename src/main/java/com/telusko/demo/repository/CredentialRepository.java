package com.telusko.demo.repository;

import com.telusko.demo.domain.UserCredentials;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends CrudRepository<UserCredentials, String> {

}
