package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Firestation;
import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FireStationRepo extends JpaRepository<Firestation, Long> {
	Firestation findById(long id);
}
