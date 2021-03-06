package com.ageo.prototype.hangoutular.services;

import java.text.ParseException;
import java.util.Collection;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ageo.prototype.hangoutular.daos.IRightDao;
import com.ageo.prototype.hangoutular.dtos.ListDto;
import com.ageo.prototype.hangoutular.dtos.RightDto;
import com.ageo.prototype.hangoutular.entities.RightEntity;
import com.ageo.prototype.hangoutular.exceptions.EntityDoesNotExistException;
import com.ageo.prototype.hangoutular.mapping.RightMapper;

@Service
public class RightService implements IRightService {

	@Autowired
	private IRightDao dao;

	@Transactional
	public Long saveOrUpdate(RightDto rightDto) throws ParseException {
		return dao.saveOrUpdate(RightMapper.dtoToEntity(rightDto));
	}

	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}

	@Transactional(readOnly = true)
	public RightDto get(Long id) throws EntityDoesNotExistException {
		RightEntity rightEntity = dao.get(id);
		
		if (rightEntity == null) {
			throw new EntityDoesNotExistException(RightEntity.class, id);
		}
		
		return RightMapper.entityToDto(rightEntity);
	}
	
	@Transactional(readOnly = true)
	public ListDto<RightDto> list(String textSearched, Integer firstResult, Integer maxResults, String orderBy, String order) {
		
		Collection<RightEntity> list = null;
		Long count = null;
		
		if (StringUtils.isEmpty(textSearched)) {
			list = dao.list(firstResult, maxResults, orderBy, order);
			count = dao.count();
		} else {
			list = dao.search(textSearched, firstResult, maxResults, orderBy, order);
			count = dao.countSearch(textSearched);
		}
		
		return new ListDto<RightDto>(RightMapper.entitiesToDtos(list), count);
	}
	
	@Transactional(readOnly = true)
	public Collection<RightDto> listAll() {
		return RightMapper.entitiesToDtos(dao.listAll());
	}

}
