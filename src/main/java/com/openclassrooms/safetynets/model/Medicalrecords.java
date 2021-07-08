package com.openclassrooms.safetynets.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Medicalrecords {

	@Id
	@GeneratedValue
	private int id;

	private String firstname;

	private String lastname;

	private Date birthdate;

	@ElementCollection
	private List<String> medications;

	@ElementCollection
	private List<String> allergies;

}
