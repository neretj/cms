package com.mojdan.app.service.tag.dto;

public class TagDTO {

	private Long id;

	private String name;
	
	public TagDTO() {}
	
	public TagDTO(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
