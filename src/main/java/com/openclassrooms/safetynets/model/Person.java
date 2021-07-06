package com.openclassrooms.safetynets.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@ToString
public class Person {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private Integer zip;

	private String phone;

	private String email;

	@Id
	@GeneratedValue (strategy= GenerationType.AUTO)
	private int id;

}
