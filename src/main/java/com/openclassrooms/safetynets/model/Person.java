package com.openclassrooms.safetynets.model;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Person {

	@JsonView(PersonViews.Normal.class)
	private String firstName;

	@JsonView(PersonViews.Normal.class)
	private String lastName;

	@JsonView(PersonViews.Normal.class)
	private String address;

	private String city;

	private Integer zip;

	@JsonView(PersonViews.Phone.class)
	private String phone;

	@JsonView(PersonViews.Mail.class)
	private String email;

	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	private Long id;

}
