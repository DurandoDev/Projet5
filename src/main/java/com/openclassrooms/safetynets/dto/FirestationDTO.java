package com.openclassrooms.safetynets.dto;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Person;
import lombok.Data;
import org.springframework.http.converter.json.MappingJacksonValue;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
public class FirestationDTO {

	Integer nbAdults;
	
	Integer nbChildren;

	List<Person> persons;
}
