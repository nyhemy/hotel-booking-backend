package io.training.catalyte.finalproject.domains.encounters;

import io.training.catalyte.finalproject.domains.patients.Patient;
import java.util.List;

/**
 * Encounter service with crud methods for encounter
 */
public interface EncounterService {

  List<Encounter> getAll(Encounter encounter);

  Encounter getById(Long id);

  Encounter createEncounter(Encounter encounter);

  Encounter updateEncounter(Long id, Encounter encounter);
}
