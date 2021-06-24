package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Medicalrecords {

	private String firstname;

	private String lastname;

	private Date birthdate;

	private List<String> medications = new ArrayList<>();

	private List<String> allergies = new ArrayList<>();

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
