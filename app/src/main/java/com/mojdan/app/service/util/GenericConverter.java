package com.mojdan.app.service.util;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

public class GenericConverter<E, T> {

	final Class<E> genericTypeEntity;
	final Class<T> genericTypeDto;

	public GenericConverter(Class<E> genericTypeEntity, Class<T> genericTypeDto) {
		this.genericTypeEntity = genericTypeEntity;
		this.genericTypeDto = genericTypeDto;
	}

	public T convertEntityToDto(E entityToConvert) {
		ModelMapper modelMapper = new ModelMapper();
		T dto = modelMapper.map(entityToConvert, genericTypeDto);
		return dto;
	}

	public E convertDTOToEntity(T dto) {
		ModelMapper modelMapper = new ModelMapper();
		E entity = modelMapper.map(dto, genericTypeEntity);
		return entity;
	}

	public List<T> convertListToDto(List<E> list) {
		List<T> listDTOs = new ArrayList<T>();
		list.forEach((entity) -> {
			listDTOs.add(convertEntityToDto(entity));
		});
		return listDTOs;
	}

	public List<E> converListToEntity(List<T> listDtos) {
		List<E> list = new ArrayList<E>();
		listDtos.forEach((dto) -> {
			list.add(convertDTOToEntity(dto));
		});
		return list;
	}

}
