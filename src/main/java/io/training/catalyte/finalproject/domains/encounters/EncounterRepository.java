package io.training.catalyte.finalproject.domains.encounters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Encounter repo
 */
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {

}
