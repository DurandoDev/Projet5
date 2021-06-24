package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Firestation {

	private String adress;

	private Integer station;

	private Long id;

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
}
