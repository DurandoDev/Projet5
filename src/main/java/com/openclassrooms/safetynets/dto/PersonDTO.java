package com.openclassrooms.safetynets.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.model.PersonViews;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class PersonDTO {

	Integer nbPersons;

	String empty = "";

	@JsonView(PersonViews.Normal.class)
	List<Person> children;

	@JsonView(PersonViews.Normal.class)
	List<Person> persons;

}
