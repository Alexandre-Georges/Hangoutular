package com.ageo.prototype.hangoutular.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ageo.prototype.hangoutular.daos.IUserDao;
import com.ageo.prototype.hangoutular.dtos.AuthenticationDto;
import com.ageo.prototype.hangoutular.dtos.LoginRequestDto;
import com.ageo.prototype.hangoutular.entities.UserEntity;
import com.ageo.prototype.hangoutular.exceptions.InvalidLoginException;

@Service
public class LoginService implements ILoginService {

	@Autowired
	private IUserDao dao;
	
	@Transactional(readOnly = true)
	public AuthenticationDto logIn(LoginRequestDto loginRequestDto) throws InvalidLoginException {
		UserEntity userEntity = dao.findByLogin(loginRequestDto.getLogin());
		
		if (userEntity == null) {
			throw new InvalidLoginException(loginRequestDto.getLogin());
		}
		
		AuthenticationDto authenticationDto = new AuthenticationDto();
		authenticationDto.setLogin(userEntity.getLogin());
		
		return authenticationDto;
	}
}
