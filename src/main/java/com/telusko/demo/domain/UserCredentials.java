package com.telusko.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserCredentials {

  @Id
  @Column(unique = true, nullable = false, length = 25)
  private String userName;
  @Column(nullable = false)
  private String password;

//  @JsonIgnore
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private User user;

  public UserCredentials() { }

  public UserCredentials(String userName, String password, User user) {
    this.userName = userName;
    this.password = password;
    this.user = user;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
