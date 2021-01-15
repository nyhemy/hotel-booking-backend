package io.training.catalyte.finalproject.repositories;

import io.training.catalyte.finalproject.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo for Patient
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
