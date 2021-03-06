package com.ageo.prototype.hangoutular.controllers;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ageo.prototype.hangoutular.dtos.AuthenticationDto;
import com.ageo.prototype.hangoutular.dtos.LoginRequestDto;
import com.ageo.prototype.hangoutular.exceptions.InvalidLoginException;
import com.ageo.prototype.hangoutular.exceptions.UserNotLoggedException;
import com.ageo.prototype.hangoutular.services.ILoginService;

@Controller
@RequestMapping("/login")
public class LoginController extends ParentController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService service;
	/*
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/sendFiles",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> sendFiles(HttpServletRequest httpServletRequest, Object command, MultipartFile file) throws InvalidLoginException {

		return new ResponseEntity<Void>(HttpStatus.OK);
	}*/
	
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/logIn",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationDto> logIn(@RequestBody LoginRequestDto loginRequestDto) throws InvalidLoginException {

		AuthenticationDto authenticationDto = service.logIn(loginRequestDto);
		
		this.auth.setAuth(authenticationDto);
		
		return new ResponseEntity<AuthenticationDto>(authenticationDto, HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/logOut")
	public ResponseEntity<Void> logOut(HttpServletRequest httpServletRequest) {

		// Destruction of the authentication object (just in case)
		this.auth.destroy();
		
		// Destruction of the server session
		httpServletRequest.getSession().invalidate();
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/getAuthentication",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationDto> getAuthentication() throws UserNotLoggedException {
		
		super.checkAuthLogin();
		
		return new ResponseEntity<AuthenticationDto>(this.auth.getAuthenticationDto(), HttpStatus.OK);
	}

	/**
	 * This handler is here because invalid login exception is local to this class
	 */
	@ExceptionHandler({ InvalidLoginException.class })
	ResponseEntity<String> handleInvalidLoginExceptions(Exception e) {
		return handleException(e, false, HttpStatus.FORBIDDEN);
	}
}
