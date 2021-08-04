package com.openclassrooms.safetynets.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.FireStationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class FirestationController {

	@Autowired
	private FireStationRepo fireStationRepo;

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

	@GetMapping(value = "/firestationPeople")
	public MappingJacksonValue listAllAtAStation(@RequestParam(value = "station")Integer station){

		List<Person> firestationList = fireStationRepo.findAllAtAStation(station);

		SimpleBeanPropertyFilter myFilter = SimpleBeanPropertyFilter.filterOutAllExcept("firstName","lastName","address","phone");

		FilterProvider filters = new SimpleFilterProvider().addFilter("monFiltre", myFilter);

		MappingJacksonValue fireStationFilters = new MappingJacksonValue(firestationList);

		fireStationFilters.setFilters(filters);

		return fireStationFilters;

	}

	@GetMapping(value = "/phoneAlert")
	public List<Person> listPhoneAtAStation(@RequestParam(value = "station")Integer station){

		return fireStationRepo.findPhoneAtAStation(station);

	}

}
