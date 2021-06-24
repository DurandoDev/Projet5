package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Person {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private Integer zip;

	private Integer phone;

	private String email;

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
