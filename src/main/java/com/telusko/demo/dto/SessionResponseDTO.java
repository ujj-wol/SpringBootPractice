package com.telusko.demo.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

//@Component
//@Scope("session")
public class SessionResponseDTO {
  private String token;
  private String name;
  private String street;

  public SessionResponseDTO() {
  }

  // used when new token is to be sent to unregistered user
  public SessionResponseDTO(String token) {
    this.token = token;
  }

  public SessionResponseDTO(String token, String name, String street) {
    this.token = token;
    this.name = name;
    this.street = street;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
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
