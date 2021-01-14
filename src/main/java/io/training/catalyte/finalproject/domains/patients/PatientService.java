package io.training.catalyte.finalproject.domains.patients;

import io.training.catalyte.finalproject.domains.encounters.Encounter;
import java.util.List;

public interface PatientService {

  List<Patient> getAll();

  Patient getById(Long id);

  List<Encounter> findEncountersByPatientId(Long id);

//  Encounter findEncounterById( Long id);

  Patient createPatient(Patient patient);

  Encounter createEncounterForPatientById(Long patientId, Encounter encounter);

  Patient updatePatient(Long id, Patient patient);

  Encounter updateEncounterByPatientId(Long patientId, Long id, Encounter encounter);

  void deletePatient(Long id);
}
