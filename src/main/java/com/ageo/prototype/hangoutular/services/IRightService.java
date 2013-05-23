package com.ageo.prototype.hangoutular.services;

import java.text.ParseException;
import java.util.Collection;

import com.ageo.prototype.hangoutular.dtos.RightDto;
import com.ageo.prototype.hangoutular.dtos.ListDto;
import com.ageo.prototype.hangoutular.exceptions.EntityDoesNotExistException;

public interface IRightService {

	public Long saveOrUpdate(RightDto rightDto) throws ParseException;
	public void delete(Long id);
	public RightDto get(Long id) throws EntityDoesNotExistException;
	public ListDto<RightDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order);
	public Collection<RightDto> listAll();
}
