package com.openclassrooms.safetynets.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Firestation {

	private String address;

	@JsonView(FirestationViews.Normal.class)
	private Long station;

	@Id
	@GeneratedValue (strategy= GenerationType.IDENTITY)
	@JsonIgnore
	private long id;
}
