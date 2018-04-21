package app.retake.repositories;

import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer>{
    Animal findOneByName(String name);
    Animal findOneByPassportSerialNumber(String passportSerialNumber);
    @Query(value = "select new app.retake.domain.dto.AnimalsJSONExportDTO(p.ownerName, a.name, a.age, p.serialNumber, p.registrationDate) " +
            "FROM Animal AS a left join a.passport AS p " +
            "WHERE p.ownerPhoneNumber = :ownerNumber")
    List<AnimalsJSONExportDTO> findAnimalsByOwnerPhoneNumber(@Param("ownerNumber")String phoneNumber);
}
