package com.openclassrooms.safetynets.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.openclassrooms.safetynets.dto.AddressDTO;
import com.openclassrooms.safetynets.dto.PersonDTO;
import com.openclassrooms.safetynets.dto.PersonWithAllergiesDTO;
import com.openclassrooms.safetynets.model.*;
import com.openclassrooms.safetynets.repository.PersonRepo;
import com.openclassrooms.safetynets.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

@RestController
public class PersonController {

	@Autowired
	private PersonRepo personRepo;

	@GetMapping(value = "person")
	public List<Person> listePersonne() {
		return personRepo.findAll();
	}

	@GetMapping(value = "person/{id}")
	public Person afficherUnePersonne(@PathVariable int id) {
		return personRepo.findById(id);
	}

	@PostMapping(value = "person")
	public ResponseEntity<Person> addPerson(@RequestBody Person person) {

		Person person1 = personRepo.save(person);

		return ResponseEntity.ok(person1);
	}

	@DeleteMapping(value = "person/{id}")
	public void deletePerson(@PathVariable long id) {
		personRepo.deleteById(id);
	}


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

	@JsonView({PersonViews.Normal.class})
	@GetMapping(value = "/childAlert")
	public PersonDTO listChildAtAnAddress(@RequestParam(value = "address") String address) {

		return PersonService.listChildAtAnAddressService(personRepo, address);

	}

	@GetMapping(value = "/fire")
	public AddressDTO listPeopleAtAStation(@RequestParam(value = "address") String address) {

		return PersonService.listPeopleAtAStationService(personRepo,address);

	}

	@GetMapping(value = "/personInfo")
	public AddressDTO searchByName(@RequestParam Map<String,String> requestParams) {

		return PersonService.searchByNameService(personRepo, requestParams);

	}

	@JsonView({PersonViews.Mail.class})
	@GetMapping(value = "/communityEmail")
	public PersonDTO listPeopleAtACity(@RequestParam(value = "city") String city) {

		List<Person> persons = personRepo.findAllByCity(city);

		PersonDTO listPerson = new PersonDTO();

		listPerson.setEmails(persons);

		return listPerson;

	}
}
