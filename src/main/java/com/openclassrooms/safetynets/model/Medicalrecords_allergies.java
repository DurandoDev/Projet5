package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Medicalrecords_allergies {
	@Id
	@GeneratedValue
	private Long id;

	//TODO
	private int medicalrecords_id;

	String allergies;

}
