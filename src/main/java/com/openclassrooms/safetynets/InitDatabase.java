package com.openclassrooms.safetynets;

import com.openclassrooms.safetynets.model.Root;
import com.openclassrooms.safetynets.repository.FireStationRepo;
import com.openclassrooms.safetynets.repository.MedicalRecordsRepo;
import com.openclassrooms.safetynets.repository.PersonRepo;
import com.openclassrooms.safetynets.services.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDatabase {

	@Autowired
	PersonRepo personRepo;

	@Autowired
	FireStationRepo fireStationRepo;

	@Autowired
	MedicalRecordsRepo medicalRecordsRepo;

	@Autowired
	FileReader fileReader;


	@PostConstruct
	public void initTables() {

		Root object = FileReader.jsonDataFromUrl();

		long checkIfTablePersonEmpty = personRepo.count();

		if (checkIfTablePersonEmpty <= 0) {
			personRepo.saveAll(object.persons);
		}

		long checkIfTableFirestationEmpty = fireStationRepo.count();

		if (checkIfTableFirestationEmpty <= 0) {
			fireStationRepo.saveAll(object.firestations);
		}

		long checkIfTableMedicalRecordsEmpty = medicalRecordsRepo.count();

		if (checkIfTableMedicalRecordsEmpty <= 0) {
			medicalRecordsRepo.saveAll(object.medicalrecords);
		}
	}
}
