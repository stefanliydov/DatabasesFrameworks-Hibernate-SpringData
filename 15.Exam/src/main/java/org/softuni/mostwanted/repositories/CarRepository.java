package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.models.Car;
import org.softuni.mostwanted.entities.models.Racer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    List<Car> findAllByRacer(Racer racer);
}
