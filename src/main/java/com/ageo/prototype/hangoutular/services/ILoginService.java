package com.ageo.prototype.hangoutular.services;

import com.ageo.prototype.hangoutular.dtos.AuthenticationDto;
import com.ageo.prototype.hangoutular.dtos.LoginRequestDto;
import com.ageo.prototype.hangoutular.exceptions.InvalidLoginException;

public interface ILoginService {

	public AuthenticationDto logIn(LoginRequestDto loginRequestDto) throws InvalidLoginException;
	
}
