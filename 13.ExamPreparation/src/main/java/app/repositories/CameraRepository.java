package app.repositories;

import app.model.entities.BasicCamera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CameraRepository extends CrudRepository<BasicCamera,Long>{

}
