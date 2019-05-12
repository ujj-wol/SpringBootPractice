package com.telusko.demo.dto;

public class UserResponseDTO {
  private String name;
  private String street;

  public UserResponseDTO() {
  }

  public UserResponseDTO(String name, String street) {
    this.name = name;
    this.street = street;
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
}
