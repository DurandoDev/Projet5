package com.openclassrooms.safetynets.model;

import com.google.gson.Gson;
import com.openclassrooms.safetynets.services.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Root {

	public List<Person> persons;
	public List<Firestation> firestations;
	public List<Medicalrecords> medicalrecords;



}
