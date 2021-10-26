package com.openclassrooms.safetynets.services;

import com.openclassrooms.safetynets.dto.AddressDTO;
import com.openclassrooms.safetynets.dto.PersonDTO;
import com.openclassrooms.safetynets.dto.PersonWithAllergiesDTO;
import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.MedicalrecordsAllergies;
import com.openclassrooms.safetynets.model.Person;
import com.openclassrooms.safetynets.repository.PersonRepo;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PersonService {

	public static PersonDTO listChildAtAnAddressService(PersonRepo personRepo, String address) {
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

	public static AddressDTO listPeopleAtAStationService(PersonRepo personRepo, String address) {
		List<Person> persons = personRepo.findPersonAtAnAddress(address);
		List<Firestation> firestations = personRepo.findFirestationsByAddress(address);

		List<PersonWithAllergiesDTO> allergies =new ArrayList<>();

		for (Person p: persons) {
			List<MedicalrecordsAllergies> mAllergies = personRepo.findAllergies(p.getId());
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

	public static AddressDTO searchByNameService(PersonRepo personRepo, Map<String,String> requestParams){
		String firstName = requestParams.get("firstName");
		String lastName = requestParams.get("lastName");

		List<Person> personList = personRepo.findAllByLastName(lastName);

		List<PersonWithAllergiesDTO> allergies =new ArrayList<>();

		for (Person p: personList) {
			List<MedicalrecordsAllergies> mAllergies = personRepo.findAllergies(p.getId());
			Medicalrecords medicalrecord = personRepo.findPersonsMedicalRecord(p.getId());

			int age = Period.between(medicalrecord.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()).getYears();

			String address = personRepo.findAddressOfAPerson(p.getLastName(), p.getFirstName());
			String email = personRepo.findMailOfAPerson(p.getLastName(), p.getFirstName());

			PersonWithAllergiesDTO allergiesDTO = new PersonWithAllergiesDTO();
			allergiesDTO.setAllergies(mAllergies);
			allergiesDTO.setName(p.getFirstName() + " " + p.getLastName());
			allergiesDTO.setPhoneNum(p.getPhone());
			allergiesDTO.setAge(age);
			allergiesDTO.setAddress(address);
			allergiesDTO.setEmail(email);

			allergies.add(allergiesDTO);
		}

		AddressDTO dto = new AddressDTO();

		dto.setPersons(allergies);

		return dto;
	}

}
