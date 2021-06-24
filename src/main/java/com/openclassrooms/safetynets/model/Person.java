package com.openclassrooms.safetynets.model;

import lombok.Data;

@Data
public class Person {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private Integer zip;

	private Integer phone;

	private String email;

}
