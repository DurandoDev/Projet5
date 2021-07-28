package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public abstract class PersonController {

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
	public void deletePerson(@PathVariable long id){personRepo.deleteById(id);}


	@PutMapping(value = "/person/{id}")
	public ResponseEntity<Person> updatePerson(@PathVariable("id") long id, @RequestBody Person person) {
		Optional<Person> personData = Optional.ofNullable(personRepo.findById(id));

		if (personData.isPresent()) {
			Person person1 = personData.get();
			person1.setAddress(person.getAddress());
			person1.setCity(person.getCity());
			person1.setZip(person.getZip());
			person1.setEmail(person.getEmail());
			person1.setPhone(person.getPhone());
			return new ResponseEntity<>(personRepo.save(person1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/childAlert?address={address}")
	public List<Person> listChildAtAnAddress(@PathVariable String address){return personRepo.findPersonUnder18YearsAtAnAddress(address);}

}
