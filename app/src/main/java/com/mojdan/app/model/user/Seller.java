package com.mojdan.app.model.user;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id") 
public class Seller extends User {

	private Integer rate;
	
	public Seller() {
		super();
	}
	
	public Seller(String username, String password, String name, String surname, Integer rate) {
		super(username, password, name, surname);
		this.rate = rate;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
}
