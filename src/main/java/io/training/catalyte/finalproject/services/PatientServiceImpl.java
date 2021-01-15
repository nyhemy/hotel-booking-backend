package io.training.catalyte.finalproject.services;

import io.training.catalyte.finalproject.entities.Encounter;
import io.training.catalyte.finalproject.entities.Patient;
import io.training.catalyte.finalproject.repositories.EncounterRepository;
import io.training.catalyte.finalproject.exceptions.BadRequest;
import io.training.catalyte.finalproject.exceptions.Conflict;
import io.training.catalyte.finalproject.exceptions.ResourceNotFound;
import io.training.catalyte.finalproject.exceptions.ServiceUnavailable;
import io.training.catalyte.finalproject.repositories.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of Patient crud methods
 */
@Service
public class PatientServiceImpl implements PatientService {

  private final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  @Autowired
  private PatientRepository patientRepository;

  @Autowired
  private EncounterRepository encounterRepository;

  @Autowired
  private EncounterService encounterService;

  /**
   * Retrieves all patients from the database.
   *
   * @return a list of all patients.
   */
  @Override
  public List<Patient> getAll() {
    List<Patient> patientList = new ArrayList<>();

    try {
      patientList = patientRepository.findAll();
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return patientList;
  }

  /**
   * Retrieves a patient from the database by its id.
   *
   * @param id the id of the patient to retrieve
   * @return the specified patient
   */
  @Override
  public Patient getById(Long id) {
    Optional<Patient> patient = Optional.ofNullable(null);

    try {
      patient = patientRepository.findById(id);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    if (patient.isEmpty()) {
      throw new ResourceNotFound();
    } else {
      return patient.get();
    }
  }

  /**
   * Retrieves all encounters for patient with provided id
   *
   * @param id is patient id
   * @return list of encounters
   */
  @Override
  public List<Encounter> findEncountersByPatientId(Long id) {

    Encounter encounterToQuery = new Encounter(
        id,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    );

    try {
      List<Encounter> encounterQueryResults = encounterService.getAll(encounterToQuery);

      if (!encounterQueryResults.isEmpty()) {
        return encounterQueryResults;

      } else {
        return new ArrayList<>();
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }
  }

  /**
   * Persists a new patient to the database.
   *
   * @param patient the patient object to persist
   * @return the persisted reservation
   */
  @Override
  public Patient createPatient(Patient patient) {
    Patient postedPatient = null;
    List<Patient> patientList = new ArrayList<>(patientRepository.findAll());

    for (Patient p : patientList) {
      if (p.getEmail().equals(patient.getEmail())) {
        throw new Conflict("Email already exists in database");
      }
    }

    try {
      postedPatient = patientRepository.save(patient);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return postedPatient;
  }

  /**
   * Creates a new encounter in the database for the specified patient
   *
   * @param patientId is id of patient to receive new encounter
   * @param encounter the encounter object to be persisted to the database
   * @return the persisted encounter
   */
  @Override
  public Encounter createEncounterForPatientById(Long patientId, Encounter encounter) {
    Encounter postedEncounter = null;

    if (!encounter.getPatientId().equals(patientId)) {
      throw new BadRequest("PatientId of encounter must match id of current patient");
    }

    try {
        postedEncounter = encounterRepository.save(encounter);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return postedEncounter;
  }

  /**
   * Updates a specified record in the database.
   *
   * @param id the id of the record to update
   * @param patient the provided patient information to persist
   * @return the updated patient
   */
  @Override
  public Patient updatePatient(Long id, Patient patient) {
    Patient updatedPatient = null;
    List<Patient> patientList = new ArrayList<>(patientRepository.findAll());
    Optional<Patient> patientToUpdate = patientRepository.findById(id);
    String patientEmail = patient.getEmail();

    if (patientToUpdate.isEmpty()) {
      throw new ResourceNotFound();
    }

    for (Patient loopPatient : patientList) {
      String patientToUpdateEmail = patientToUpdate.get().getEmail();
      String loopPatientEmail = loopPatient.getEmail();

      if (!loopPatientEmail.equals(patientToUpdateEmail)
          && loopPatientEmail.equals(patientEmail)) {
        throw new Conflict("Email already exists in database");
      }
    }

    try {
      updatedPatient = patientRepository.save(patient);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return updatedPatient;
  }

  /**
   * Updates a specified encounter for a patient in the database.
   *
   * @param patientId is the id of the patient to whom the encounter belongs
   * @param id is the id of the encounter that will be updated
   * @param encounter is the provided encounter information to persist
   * @return the updated encounter
   */
  @Override
  public Encounter updateEncounterByPatientId(Long patientId, Long id, Encounter encounter) {
    Encounter updatedEncounter = null;
    Optional<Encounter> encounterToUpdate = encounterRepository.findById(id);

    if (!encounter.getPatientId().equals(patientId)) {
      throw new BadRequest("patientId of encounter must match id of current patient");
    }

    if (encounterToUpdate.isEmpty()) {
      throw new ResourceNotFound();
    } else if (!encounterToUpdate.get().getPatientId().equals(patientId)) {
      throw new ResourceNotFound(
          "patientId found in encounter did not match patient id param in request"
      );
    }

    try {
      updatedEncounter = encounterRepository.save(encounter);

    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return updatedEncounter;
  }

  /**
   * Deletes a patient by id from the database.
   *
   * @param id the id of the reservation to delete
   */
  @Override
  public void deletePatient(Long id) {
    if (!findEncountersByPatientId(id).isEmpty()) {
      throw new Conflict("Cannot delete patient with existing encounters");
    }

    try {
      if (patientRepository.existsById(id)) {
        patientRepository.deleteById(id);
        return;
      }

    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    throw new ResourceNotFound("Patient does not exist in database");
  }
}
