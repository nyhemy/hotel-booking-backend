package io.training.catalyte.finalproject.repositories;

import io.training.catalyte.finalproject.entities.Encounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Encounter repo
 */
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {

}
