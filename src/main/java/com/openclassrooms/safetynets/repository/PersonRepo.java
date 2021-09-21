package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.Medicalrecords_allergies;
import com.openclassrooms.safetynets.model.Person;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
	Person findById(long id);
	Person save(Person person);


	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstname AND p.lastName = m.lastname " +
			"WHERE p.address = ?1 AND m.birthdate > ?2 ")
	List<Person> findPersonUnder18YearsAtAnAddress(String address, Date birthdate);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstname AND p.lastName = m.lastname " +
			"WHERE p.address = ?1 AND m.birthdate < ?2 ")
	List<Person> findPersonOver18YearsAtAnAddress(String address, Date birthdate);

	@Query(value = "SELECT p FROM Person p WHERE p.address = ?1")
	List<Person> findPersonAtAnAddress(String address);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstname AND p.lastName = m.lastname " +
			"INNER JOIN Firestation f ON p.address = f.address " +
			"WHERE f.id= ?1 AND m.birthdate > '2003-07-27'")
	List<Person> findPersonUnder18YearsByFirestation(Integer firestationId);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstname AND p.lastName = m.lastname " +
			"INNER JOIN Firestation f ON p.address = f.address " +
			"WHERE f.id= ?1 AND m.birthdate < '2003-07-27'")
	List<Person> findPersonOver18YearsByFirestation(Integer firestationId);

//	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords_allergies m ON p.firstName = m.firstname AND p.lastName = m.lastname")
//	List<Medicalrecords> findMedicationsAndAllergies(String address);

	@Query(value = "SELECT f FROM Firestation f WHERE f.address= ?1")
	List<Firestation> findFirestationsByAddress(String address);

	@Query(value = "SELECT p FROM Person p WHERE p.city = ?1")
	List<Person> findAllByCity(String city);

	@Query(value = "SELECT m FROM Medicalrecords_allergies m INNER JOIN Medicalrecords r ON m.medicalrecords_id = r.id INNER JOIN Person p ON p.firstName = r.firstname AND p.lastName = r.lastname WHERE p.id = ?1")
	List<Medicalrecords_allergies> findAllergies(Long id);

	@Query(value = "SELECT m FROM Medicalrecords m INNER JOIN Person p ON m.firstname = p.firstName AND m.lastname = p.lastName WHERE p.id =?1")
	Medicalrecords findPersonsMedicalRecord(Long id);

	@Query(value = "SELECT f.address FROM Firestation f WHERE f.station = ?1")
	List<String> findAddressByStation(Integer station);
}
