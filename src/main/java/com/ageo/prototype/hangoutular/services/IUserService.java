package com.ageo.prototype.hangoutular.services;

import java.text.ParseException;

import com.ageo.prototype.hangoutular.dtos.ListDto;
import com.ageo.prototype.hangoutular.dtos.UserDto;
import com.ageo.prototype.hangoutular.exceptions.EntityDoesNotExistException;

public interface IUserService {

	public Long saveOrUpdate(UserDto userDto) throws ParseException;
	public void delete(Long id);
	public UserDto get(Long id) throws EntityDoesNotExistException;
	public ListDto<UserDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	
}
