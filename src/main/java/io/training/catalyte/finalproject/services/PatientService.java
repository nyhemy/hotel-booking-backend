package io.training.catalyte.finalproject.services;

import io.training.catalyte.finalproject.entities.Encounter;
import io.training.catalyte.finalproject.entities.Patient;
import java.util.List;

/**
 * Service for Patient containing patient crud methods as well as some crud for encounters that
 * depend on a Patient
 */
public interface PatientService {

  List<Patient> getAll();

  Patient getById(Long id);

  List<Encounter> findEncountersByPatientId(Long id);

  Patient createPatient(Patient patient);

  Encounter createEncounterForPatientById(Long patientId, Encounter encounter);

  Patient updatePatient(Long id, Patient patient);

  Encounter updateEncounterByPatientId(Long patientId, Long id, Encounter encounter);

  void deletePatient(Long id);
}
