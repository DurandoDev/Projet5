package com.openclassrooms.safetynets.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.openclassrooms.safetynets.dto.AddressDTO;
import com.openclassrooms.safetynets.dto.PersonDTO;
import com.openclassrooms.safetynets.dto.PersonWithAllergiesDTO;
import com.openclassrooms.safetynets.model.*;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
	public ResponseEntity<Void> addPerson(@RequestBody Person person) {

		Person person1 = personRepo.save(person);

		if (person == null) {
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

		LocalDate dateNow = LocalDate.now();
		dateNow = dateNow.minusYears(18);

		ZoneId defaultZoneId = ZoneId.systemDefault();

		Date date = Date.from(dateNow.atStartOfDay(defaultZoneId).toInstant());

		List<Person> personList = personRepo.findPersonUnder18YearsAtAnAddress(address, date);

		List<Person> nbPersons = personRepo.findPersonOver18YearsAtAnAddress(address, date);

		PersonDTO listPerson = new PersonDTO();

		listPerson.setChildren(personList);
		listPerson.setPersons(nbPersons);

		return listPerson;

	}

	@GetMapping(value = "/fire")
	public AddressDTO listPeopleAtAStation(@RequestParam(value = "address") String address) {

		List<Person> persons = personRepo.findPersonAtAnAddress(address);
		List<Firestation> firestations = personRepo.findFirestationsByAddress(address);

		List<PersonWithAllergiesDTO> allergies =new ArrayList<>();

		for (Person p: persons) {
			List<Medicalrecords_allergies> mAllergies = personRepo.findAllergies(p.getId());
			Medicalrecords medicalrecord = personRepo.findPersonsMedicalRecord(p.getId());

			int age = Period.between(medicalrecord.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

			PersonWithAllergiesDTO allergiesDTO = new PersonWithAllergiesDTO();
			allergiesDTO.setAllergies(mAllergies);
			allergiesDTO.setName(p.getFirstName() + " " + p.getLastName());
			allergiesDTO.setPhoneNum(p.getPhone());
			allergiesDTO.setAge(age);

			allergies.add(allergiesDTO);
		}

		AddressDTO dto = new AddressDTO();

		dto.setAddress(address);
		dto.setFirestationNumber(firestations.get(0).getStation());
		dto.setPersons(allergies);

		return dto;

	}

	@GetMapping(value = "/personInfo")
	public AddressDTO searchByName(@RequestParam Map<String,String> requestParams) {
		String firstName = requestParams.get("firstName");
		String lastName = requestParams.get("lastName");

		List<Person> personList = personRepo.findAllByLastName(lastName);

		List<PersonWithAllergiesDTO> allergies =new ArrayList<>();

		for (Person p: personList) {
			List<Medicalrecords_allergies> mAllergies = personRepo.findAllergies(p.getId());
			Medicalrecords medicalrecord = personRepo.findPersonsMedicalRecord(p.getId());

			int age = Period.between(medicalrecord.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

			PersonWithAllergiesDTO allergiesDTO = new PersonWithAllergiesDTO();
			allergiesDTO.setAllergies(mAllergies);
			allergiesDTO.setName(p.getFirstName() + " " + p.getLastName());
			allergiesDTO.setPhoneNum(p.getPhone());
			allergiesDTO.setAge(age);

			allergies.add(allergiesDTO);
		}

		AddressDTO dto = new AddressDTO();

		dto.setPersons(allergies);

		return dto;


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
