package com.openclassrooms.safetynets.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@JsonIgnoreProperties(value = {"id","city","zip","email"})
@JsonFilter("monFiltre")
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
	private long id;

}
