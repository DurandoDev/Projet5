package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {
	Person findById(int id);
}
