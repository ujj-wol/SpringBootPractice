package com.telusko.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Address {

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 40)
	private String id;

	@Column(length = 20)
	private String street;
	
	public Address() {
		this.id = generateAddressId();
	}
	
	public Address(String street) {
		super();
		this.id = generateAddressId();
		this.street = street;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = generateAddressId();
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	private String generateAddressId() {
    String uniqueID = UUID.randomUUID().toString();
    return uniqueID;
  }
	
	
}
