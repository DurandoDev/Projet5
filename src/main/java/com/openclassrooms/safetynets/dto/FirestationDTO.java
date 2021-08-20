package com.openclassrooms.safetynets.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.model.PersonViews;
import lombok.Data;
import java.util.List;

@Data
public class FirestationDTO {

	@JsonView(PersonViews.Normal.class)
	Integer nbAdults;

	@JsonView(PersonViews.Normal.class)
	Integer nbChildren;

	@JsonView(PersonViews.Normal.class)
	List<Person> persons;

	@JsonView(PersonViews.Phone.class)
	List<Person> phones;
}
