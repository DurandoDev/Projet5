package com.openclassrooms.safetynets.controller;

import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class MedicalRecordsController {

	@Autowired
	private MedicalRecordsRepo medicalRecordsRepo;

	@GetMapping(value = "medicalrecords")
	public List<Medicalrecords> listeMedicalRecords() { return medicalRecordsRepo.findAll(); }

	@GetMapping(value = "medicalrecords/{id}")
	public Medicalrecords afficherUneMedicalRecords(@PathVariable long id){return medicalRecordsRepo.findById(id);}

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
	public void deleteMedicalRecords(@PathVariable long id){medicalRecordsRepo.deleteById(id);}

	@PutMapping(value = "/medicalrecords/{id}")
	public ResponseEntity<Medicalrecords> updateMedicalRecords(@PathVariable("id") long id, @RequestBody Medicalrecords medicalrecords) {
		Optional<Medicalrecords> medicalrecordsData = Optional.ofNullable(medicalRecordsRepo.findById(id));

		if (medicalrecordsData.isPresent()) {
			Medicalrecords medicalrecords1 = medicalrecordsData.get();
			medicalrecords1.setMedications(medicalrecords.getMedications());
			medicalrecords1.setAllergies(medicalrecords.getAllergies());
			medicalrecords1.setBirthdate(medicalrecords.getBirthdate());
			return new ResponseEntity<>(medicalRecordsRepo.save(medicalrecords1), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
