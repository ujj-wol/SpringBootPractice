package com.telusko.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.telusko.demo.exception.*;

@ControllerAdvice
@RestController
public class CustomErrorController extends ResponseEntityExceptionHandler{

//	@ExceptionHandler(NotFoundException.class)
//	  public final Map handleNotFoundException(NotFoundException ex) {
//	    Map response = new HashMap<>();
//	    response.put("status", HttpStatus.NOT_FOUND.value());
//	    response.put("message", ex.getMessage());
//	    return response;
//	  }
	
	@ExceptionHandler(ServerException.class)
	  public final Map handleServerException(ServerException ex) {
	    Map response = new HashMap<>();
	    response.put("status", ex.getStatus());
	    response.put("message", ex.getMessage());
	    return response;
	  }

}
