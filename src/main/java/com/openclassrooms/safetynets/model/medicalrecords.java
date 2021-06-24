package com.openclassrooms.safetynets.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class medicalrecords {

	private String firstname;

	private String lastname;

	private Date birthdate;

	private List<String> medications = new ArrayList<String>();

	private List<String> allergies = new ArrayList<String>();
}
