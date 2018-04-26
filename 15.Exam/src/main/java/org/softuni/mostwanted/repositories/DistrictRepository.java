package org.softuni.mostwanted.repositories;

import org.softuni.mostwanted.entities.models.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District,Integer> {
    District findOneByName(String name);

    District getOneByName(String districtName);
}
