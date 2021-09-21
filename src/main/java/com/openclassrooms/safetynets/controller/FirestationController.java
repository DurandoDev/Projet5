package com.openclassrooms.safetynets.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.openclassrooms.safetynets.dto.AddressDTO;
import com.openclassrooms.safetynets.dto.FirestationDTO;
import com.openclassrooms.safetynets.dto.PersonWithAllergiesDTO;
import com.openclassrooms.safetynets.model.*;
import com.openclassrooms.safetynets.repository.FireStationRepo;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FirestationController {

	@Autowired
	private FireStationRepo fireStationRepo;

	@Autowired
	private PersonRepo personRepo;

	@GetMapping(value = "firestation")
	public List<Firestation> listeFirestation() { return fireStationRepo.findAll(); }

	@GetMapping(value = "firestation/{id}")
	public Firestation afficherUneFireStation(@PathVariable int id){return fireStationRepo.findById(id);}

	@PostMapping(value = "firestation")
	public ResponseEntity<Void> addFireStation(@RequestBody Firestation firestation){

		Firestation firestation1 = fireStationRepo.save(firestation);

		if (firestation == null){
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(firestation1.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "firestation/{id}")
	public void deleteFirestation(@PathVariable long id){fireStationRepo.deleteById(id);}

	@PutMapping(value = "/firestation/{id}")
	public ResponseEntity<Firestation> updateFirestation(@PathVariable("id") long id, @RequestBody Firestation firestation) {
		Optional<Firestation> firestationData = Optional.ofNullable(fireStationRepo.findById(id));

		if (firestationData.isPresent()) {
			Firestation firestation1 = firestationData.get();
			firestation1.setAddress(firestation.getAddress());
			firestation1.setStation(firestation.getStation());
			return new ResponseEntity<>(fireStationRepo.save(firestation1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@JsonView({PersonViews.NormalPhone.class})
	@GetMapping(value = "/firestationPeople")
	public FirestationDTO listAllAtAStation(@RequestParam(value = "station")Integer station){

		List<Person> persons = fireStationRepo.findAllAtAStation(station);

		List<Person> children = personRepo.findPersonUnder18YearsByFirestation(station);

		List<Person> adults = personRepo.findPersonOver18YearsByFirestation(station);

		FirestationDTO listPerson = new FirestationDTO();

		listPerson.setPersons(persons);
		listPerson.setNbChildren(children.size());
		listPerson.setNbAdults(adults.size());

		return listPerson;

	}

	@JsonView(PersonViews.Phone.class)
	@GetMapping(value = "/phoneAlert")
	public FirestationDTO listPhoneAtAStation(@RequestParam(value = "station")Integer station){

		List<Person> phones = fireStationRepo.findPhoneAtAStation(station);

		FirestationDTO listPhones = new FirestationDTO();

		listPhones.setPhones(phones);

		return listPhones;

	}

//	@JsonView({PersonViews.NormalPhone.class})
	@GetMapping(value = "/flood")
	public List<AddressDTO> listPeopleAtAStation(@RequestParam(value = "station")Integer station) {

		List<AddressDTO> listAddress = new ArrayList<>();

		List<String> address = personRepo.findAddressByStation(station);

		FirestationDTO listPeople = new FirestationDTO();


		List<PersonWithAllergiesDTO> allergies = null;
		for (String a : address) {

			AddressDTO dto = new AddressDTO();
			List<Person> persons = personRepo.findPersonAtAnAddress(a);

			allergies = new ArrayList<>();

			for (Person p : persons) {
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

			dto.setPersons(allergies);
			dto.setAddress(a);
			dto.setFirestationNumber(station);
			listAddress.add(dto);
		}

		return listAddress;

	}



}
