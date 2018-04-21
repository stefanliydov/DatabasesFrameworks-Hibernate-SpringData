package app.repositories;

import app.model.entities.Workshop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkshopRepository extends CrudRepository<Workshop,Long> {
}
