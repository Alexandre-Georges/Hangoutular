package com.ageo.prototype.hangoutular.beans;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ageo.prototype.hangoutular.dtos.AuthenticationDto;
import com.ageo.prototype.hangoutular.exceptions.UserNotLoggedException;

@Scope(value = "session")
@Component
public class Auth implements Serializable {

	private static final long serialVersionUID = 1236635420647497830L;
	
	private AuthenticationDto authenticationDto = null;
	
	public void setAuth(AuthenticationDto authenticationDto) {
		this.authenticationDto = authenticationDto;
	}

	public void checkIsLogged() throws UserNotLoggedException {
		if (this.authenticationDto == null) {
			throw new UserNotLoggedException();
		}
	}
	
	public void destroy() {
		this.authenticationDto = null;
	}

	public AuthenticationDto getAuthenticationDto() {
		return authenticationDto;
	}
	
}
