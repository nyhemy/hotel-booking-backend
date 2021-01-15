package io.training.catalyte.finalproject.domains.patients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repo for Patient
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
