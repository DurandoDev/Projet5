package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PersonController {

	@Autowired
	private PersonRepo personRepo;

	@Autowired
	private MedicalRecordsRepo medicalRecordsRepo;

	@GetMapping(value = "Persons")
	public List<Person> listePersonne() { return personRepo.findAll(); }

	@GetMapping(value = "Persons/{id}")
	public Person afficherUnePersonne(@PathVariable int id){return personRepo.findById(id);}

	@PostMapping(value = "Persons")
	public ResponseEntity<Void> addPerson(@RequestBody Person person){

		Person person1 = personRepo.save(person);

		if (person == null){
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(person1.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

}
