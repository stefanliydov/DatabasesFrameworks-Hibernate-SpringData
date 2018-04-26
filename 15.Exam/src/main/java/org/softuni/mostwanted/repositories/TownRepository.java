package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.models.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town findOneByName(String name);



}
