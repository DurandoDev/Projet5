package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
	Person findById(long id);
	Person save(Person person);


	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstName AND p.lastName = m.lastName " +
			"WHERE p.address = ?1 AND m.birthdate > ?2 ")
	List<Person> findPersonUnder18YearsAtAnAddress(String address, Date birthdate);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstName AND p.lastName = m.lastName " +
			"WHERE p.address = ?1 AND m.birthdate < ?2 ")
	List<Person> findPersonOver18YearsAtAnAddress(String address, Date birthdate);

	@Query(value = "SELECT p FROM Person p WHERE p.address = ?1")
	List<Person> findPersonAtAnAddress(String address);

	@Query(value = "SELECT p.address FROM Person p WHERE p.lastName = ?1 AND p.firstName = ?2")
	String findAddressOfAPerson(String lastName, String firstName);

	@Query(value = "SELECT p.email FROM Person p WHERE p.lastName = ?1 AND p.firstName = ?2")
	String findMailOfAPerson(String lastName, String firstName);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstName AND p.lastName = m.lastName " +
			"INNER JOIN Firestation f ON p.address = f.address " +
			"WHERE f.station= ?1 AND m.birthdate > '2003-07-27'")
	List<Person> findPersonUnder18YearsByFirestation(Long firestationId);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstName AND p.lastName = m.lastName " +
			"INNER JOIN Firestation f ON p.address = f.address " +
			"WHERE f.station= ?1 AND m.birthdate < '2003-07-27'")
	List<Person> findPersonOver18YearsByFirestation(Long firestationId);

	@Query(value = "SELECT f FROM Firestation f WHERE f.address= ?1")
	List<Firestation> findFirestationsByAddress(String address);

	@Query(value = "SELECT p FROM Person p WHERE p.city = ?1")
	List<Person> findAllByCity(String city);


	@Query(value = "SELECT m FROM Medicalrecords m INNER JOIN Person p ON m.firstName = p.firstName AND m.lastName = p.lastName WHERE p.id =?1")
	Medicalrecords findPersonsMedicalRecord(Long id);

	@Query(value = "SELECT f.address FROM Firestation f WHERE f.station = ?1")
	List<String> findAddressByStation(Long station);

	@Query(value = "SELECT p FROM Person p WHERE p.lastName = ?1")
	List<Person> findAllByLastName(String lastName);
}
