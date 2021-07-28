package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
	Person findById(long id);
	Person save(Person person);

	@Query(value = "SELECT p FROM Person p INNER JOIN Medicalrecords m ON p.firstName = m.firstname AND p.lastName = m.lastname " +
			"WHERE p.address = ?1 AND m.birthdate > '2003-07-27'")
	List<Person> findPersonUnder18YearsAtAnAddress(String address);

}
