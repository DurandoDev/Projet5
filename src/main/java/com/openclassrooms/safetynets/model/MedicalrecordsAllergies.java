package com.openclassrooms.safetynets.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class MedicalrecordsAllergies {
	@Id
	@GeneratedValue
	private Long id;

	private int medicalrecords_id;

	String allergies;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		MedicalrecordsAllergies that = (MedicalrecordsAllergies) o;

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return 418422499;
	}
}
