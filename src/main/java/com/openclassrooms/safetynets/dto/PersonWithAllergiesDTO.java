package com.openclassrooms.safetynets.dto;

import com.openclassrooms.safetynets.model.Medicalrecords;
import lombok.Data;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
public class PersonWithAllergiesDTO {

	String name;

	String phoneNum;

	String address;

	String email;

	Integer age;

	private List<String> medications;

	private List<String> allergies;

}
