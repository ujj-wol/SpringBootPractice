package com.telusko.demo.repository;

import com.telusko.demo.domain.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface AddressRepository extends JpaRepository<Address, String> {
public interface AddressRepository extends CrudRepository<Address, String> {

}
