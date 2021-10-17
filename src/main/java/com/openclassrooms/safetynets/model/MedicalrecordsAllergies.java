package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class MedicalrecordsAllergies {
	@Id
	@GeneratedValue
	private Long id;

	//TODO
	private int medicalrecords_id;

	String allergies;

}
