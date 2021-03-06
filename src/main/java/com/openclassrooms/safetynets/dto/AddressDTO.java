package com.openclassrooms.safetynets.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddressDTO {

	String address;

	Long firestationNumber;

	List<PersonWithAllergiesDTO> persons;
}
