package com.telusko.demo.controller;

import java.util.List;

import com.telusko.demo.domain.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.demo.exception.*;
import com.telusko.demo.repository.AddressRepository;

@RestController
@RequestMapping("/address")
public class AddressController {
	static Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	AddressRepository addressRepository;

	@RequestMapping("")
	public List<Address> getAddresses() {
		return (List<Address>) addressRepository.findAll();
	}
	
	@RequestMapping(path = "/{id}", produces = "application/json")
	public Address getAddress(@PathVariable("id") String addressId) {
		Address address;
		address = addressRepository.findById(addressId).orElse(null);
		
		if(address == null) {
			logger.info("Address not found, address_id = {}", addressId);
			throw new ServerException("Address Not Found", HttpStatus.NOT_FOUND.value());
		}
		return address;
	}
	
	@PostMapping("")
	public Address addAddress(@RequestBody() Address address) {
		Address a = addressRepository.save(address);
		return a;
	}
}
