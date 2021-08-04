package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class FirestationDTO {
	private String address;

	private Integer station;

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
}
