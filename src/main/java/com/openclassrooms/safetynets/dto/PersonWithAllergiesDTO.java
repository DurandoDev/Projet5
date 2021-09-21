package com.openclassrooms.safetynets.dto;

import com.openclassrooms.safetynets.model.Medicalrecords_allergies;
import lombok.Data;

import java.util.List;

@Data
public class PersonWithAllergiesDTO {

	String name;

	String phoneNum;

	Integer age;

	List<Medicalrecords_allergies> allergies;
}
