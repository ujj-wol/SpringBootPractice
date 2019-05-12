package com.telusko.demo.exception;


public class ServerException  extends RuntimeException {
	  private Integer status;
	  public ServerException(String message, Integer status) {
		  super(message);
		  this.status = status;
	  }
	  
	  public Integer getStatus() {
		  return status;
	  }
	  
}
