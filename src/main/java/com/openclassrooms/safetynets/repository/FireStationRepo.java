package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FireStationRepo extends JpaRepository<Firestation, Long> {
	//Firestation findById(Long id);

	Firestation save(Firestation firestation);

	@Query(value = "SELECT p FROM Person p INNER JOIN Firestation f ON p.address = f.address WHERE f.station = ?1")
	List<Person> findAllAtAStation(Integer station);

	@Query(value = "SELECT p FROM Person p INNER JOIN Firestation f ON p.address = f.address WHERE f.station = ?1")
	List<Person> findPhoneAtAStation(Integer station);

//	@Query(value = "SELECT f.address FROM Firestation f WHERE f.station = ?1")
//	List<String> findAddressByStation(Integer station);

}
