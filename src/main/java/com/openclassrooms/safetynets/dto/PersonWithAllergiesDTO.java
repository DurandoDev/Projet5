package com.openclassrooms.safetynets.dto;

import com.openclassrooms.safetynets.model.MedicalrecordsAllergies;
import lombok.Data;

import java.util.List;

@Data
public class PersonWithAllergiesDTO {

	String name;

	String phoneNum;

	String address;

	String email;

	Integer age;

	List<MedicalrecordsAllergies> allergies;
}
