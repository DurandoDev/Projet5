package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.repository.FireStationRepo;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

public class MedicalRecordsController {

	@Autowired
	private MedicalRecordsRepo medicalRecordsRepo;

	@GetMapping(value = "medicalrecords")
	public List<Medicalrecords> listeMedicalRecords() { return medicalRecordsRepo.findAll(); }

	@GetMapping(value = "medicalrecords/{id}")
	public Medicalrecords afficherUneMedicalRecords(@PathVariable int id){return medicalRecordsRepo.findById(id);}

	@PostMapping(value = "medicalrecords")
	public ResponseEntity<Void> addMedicalRecords(@RequestBody Medicalrecords medicalrecords){

		Medicalrecords medicalrecords1 = medicalRecordsRepo.save(medicalrecords);

		if (medicalrecords == null){
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(medicalrecords1.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "medicalrecords/{id}")
	public void deleteMedicalRecords(@PathVariable int id){medicalRecordsRepo.deleteById(id);}
}
