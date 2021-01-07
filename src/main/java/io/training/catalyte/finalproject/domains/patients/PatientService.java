package io.training.catalyte.finalproject.domains.patients;

import io.training.catalyte.finalproject.domains.encounters.Encounter;
import java.util.List;

public interface PatientService {

  List<Patient> getAll();

  Patient getById(Long id);

  List<Encounter> findEncountersByPatientId(Long id);

  Patient createPatient(Patient patient);

  Patient updatePatient(Long id, Patient patient);

  void deletePatient(Long id);
}
