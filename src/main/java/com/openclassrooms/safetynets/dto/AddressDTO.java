package com.openclassrooms.safetynets.dto;

import com.openclassrooms.safetynets.model.Person;
import lombok.Data;

import java.util.List;

@Data
public class AddressDTO {

	String address;

	Integer firestationNumber;

	List<PersonWithAllergiesDTO> persons;
}
