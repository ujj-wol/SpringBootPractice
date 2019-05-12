package com.telusko.demo.dto;

public class UserRegistrationDTO {

  private String name;
  private String password;
//  private String addressId;
  private String street;

  public UserRegistrationDTO() {
  }

  public UserRegistrationDTO(String name, String addressId, String street, String password) {
    this.name = name;
    this.street = street;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
