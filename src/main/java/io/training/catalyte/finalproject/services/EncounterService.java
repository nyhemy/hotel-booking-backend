package io.training.catalyte.finalproject.services;

import io.training.catalyte.finalproject.entities.Encounter;
import java.util.List;

/**
 * Encounter service with crud methods for encounter
 */
public interface EncounterService {

  List<Encounter> getAll(Encounter encounter);

  Encounter getById(Long id);
}
