package io.training.catalyte.finalproject.domains.encounters;

import io.training.catalyte.finalproject.domains.patients.Patient;
import io.training.catalyte.finalproject.exceptions.ServiceUnavailable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EncounterServiceImpl implements EncounterService {

  private final Logger logger = LoggerFactory
      .getLogger(io.training.catalyte.finalproject.domains.patients.PatientServiceImpl.class);

  @Autowired
  private EncounterRepository encounterRepository;

  /**
   * Retrieves all encounters from the database.
   *
   * @return a list of all encounters.
   */
  @Override
  public List<Encounter> getAll(Encounter encounter) {
    List<Encounter> encounterList = new ArrayList<>();

    try {
      if (encounter.isEmpty()) {
        encounterList = encounterRepository.findAll();
      } else {
        Example<Encounter> encounterExample = Example.of(encounter);
        return encounterRepository.findAll(encounterExample);
      }

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return encounterList;
  }

  /**
   * Retrieves an encounter from the database by its id.
   *
   * @param id the id of the encounter to retrieve
   * @return the specified encounter
   */
  @Override
  public Encounter getById(Long id) {
    Optional<Encounter> encounter = Optional.ofNullable(null);

    try {
      encounter = encounterRepository.findById(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    if (encounter.isEmpty()) {
      throw new ResourceNotFoundException();
    } else {
      return encounter.get();
    }
  }

  /**
   * Persists a new encounter to the database.
   *
   * @param encounter the encounter object to persist
   * @return the persisted encounter
   */
  @Override
  public Encounter createEncounter(Encounter encounter) {
    Encounter postedEncounter = null;

    try {
      postedEncounter = encounterRepository.save(encounter);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return postedEncounter;
  }

  /**
   * Updates a specified record in the database.
   *
   * @param id the id of the record to update
   * @param encounter the provided encounter information to persist
   * @return the updated encounter
   */
  @Override
  public Encounter updateEncounter(Long id, Encounter encounter) {
    Encounter updatedEncounter = null;

    try {
      Optional<Encounter> encounterToUpdate = encounterRepository.findById(id);
      if (encounterToUpdate.isEmpty()) {
        throw new ResourceNotFoundException();
      } else {
        updatedEncounter = encounterRepository.save(encounter);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable(e);
    }

    return updatedEncounter;
  }
}
