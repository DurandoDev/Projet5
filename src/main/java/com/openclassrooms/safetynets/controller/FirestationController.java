package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.FireStationRepo;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import com.openclassrooms.safetynets.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class FirestationController {

	@Autowired
	private FireStationRepo fireStationRepo;

	@Autowired
	private MedicalRecordsRepo medicalRecordsRepo;

	@GetMapping(value = "Firestations")
	public List<Firestation> listeFirestation() { return fireStationRepo.findAll(); }

	@GetMapping(value = "Firestations/{id}")
	public Firestation afficherUneFireStation(@PathVariable int id){return fireStationRepo.findById(id);}

	@PostMapping(value = "Firestations")
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

}
