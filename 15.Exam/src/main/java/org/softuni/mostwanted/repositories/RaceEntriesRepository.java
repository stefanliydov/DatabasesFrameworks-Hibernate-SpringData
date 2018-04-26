package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.models.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceEntriesRepository extends JpaRepository<RaceEntry,Integer> {

}
