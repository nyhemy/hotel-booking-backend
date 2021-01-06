package io.training.catalyte.finalproject.domains.patients;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

public class PatientServiceImpl implements PatientService {

  private final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

  @Autowired
  private PatientRepository patientRepository;

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
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
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
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    if (patient.isEmpty()) {
      throw new ResourceNotFoundException();
    } else {
      return patient.get();
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

    try {
      postedPatient = patientRepository.save(patient);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return postedPatient;
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

    try {
      Optional<Patient> patientToUpdate = patientRepository.findById(id);
      if (patientToUpdate.isEmpty()) {
        throw new ResourceNotFoundException();
      } else {
        updatedPatient = patientRepository.save(patient);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }

    return updatedPatient;
  }

  /**
   * Deletes a patient by id from the database.
   *
   * @param id the id of the reservation to delete
   */
  @Override
  public void deletePatient(Long id) {
    try {
      patientRepository.deleteById(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
    }
  }
}
