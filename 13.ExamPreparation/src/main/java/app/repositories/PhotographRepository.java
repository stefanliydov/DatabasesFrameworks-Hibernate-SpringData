package app.repositories;

import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotographRepository extends JpaRepository<Photographer,Long> {
}
