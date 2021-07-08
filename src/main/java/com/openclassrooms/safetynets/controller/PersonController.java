package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

	@Autowired
	private PersonRepo personRepo;

	@GetMapping(value = "person")
	public List<Person> listePersonne() { return personRepo.findAll(); }

	@GetMapping(value = "person/{id}")
	public Person afficherUnePersonne(@PathVariable int id){return personRepo.findById(id);}

	@PostMapping(value = "person")
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

	@DeleteMapping(value = "person/{id}")
	public void deletePerson(@PathVariable int id){personRepo.deleteById(id);}

//	@PutMapping(value = "person/{id}")
//	Person updatePerson(@PathVariable("id")  int id, @RequestBody Person person) {
//		return personRepo.findById(id)
//				.map(person -> {
//					person.getAddress
//				})
//
//
//
//	}

}
