package com.openclassrooms.safetynets.services;

import com.openclassrooms.safetynets.dto.AddressDTO;
import com.openclassrooms.safetynets.dto.FirestationDTO;
import com.openclassrooms.safetynets.dto.PersonWithAllergiesDTO;
import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.MedicalrecordsAllergies;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.PersonRepo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FiresationService {

	public static List<AddressDTO> listPeopleAtAStationService(PersonRepo personRepo, Long station) {
		List<AddressDTO> listAddress = new ArrayList<>();

		List<String> address = personRepo.findAddressByStation(station);

		FirestationDTO listPeople = new FirestationDTO();

		List<PersonWithAllergiesDTO> allergies;
		for (String a : address) {

			AddressDTO dto = new AddressDTO();
			List<Person> persons = personRepo.findPersonAtAnAddress(a);

			allergies = new ArrayList<>();

			for (Person p : persons) {
				List<MedicalrecordsAllergies> mAllergies = personRepo.findAllergies(p.getId());
				Medicalrecords medicalrecord = personRepo.findPersonsMedicalRecord(p.getId());

				int age = Period.between(medicalrecord.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

				PersonWithAllergiesDTO allergiesDTO = new PersonWithAllergiesDTO();
				allergiesDTO.setAllergies(mAllergies);
				allergiesDTO.setName(p.getFirstName() + " " + p.getLastName());
				allergiesDTO.setPhoneNum(p.getPhone());
				allergiesDTO.setAge(age);
				allergiesDTO.setAddress(p.getAddress());
				allergiesDTO.setEmail(p.getEmail());

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
