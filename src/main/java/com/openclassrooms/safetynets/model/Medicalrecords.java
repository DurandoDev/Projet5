package com.openclassrooms.safetynets.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Medicalrecords {

	@Id
	@GeneratedValue
	private long id;

	private String firstName;

	private String lastName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private Date birthdate;

	@ElementCollection
	private List<String> medications;

	@ElementCollection
	private List<String> allergies;

}
