package com.telusko.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.telusko.demo.domain.User;

@Repository
//public interface UserRepository extends JpaRepository<User, Long> {
public interface UserRepository extends CrudRepository<User, Long> {

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@Override
//	User save(User u);
}
