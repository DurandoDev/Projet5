package com.openclassrooms.safetynets.repository;

import com.openclassrooms.safetynets.model.Medicalrecords;
import com.openclassrooms.safetynets.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordsRepo extends JpaRepository<Medicalrecords, Long> {
}
