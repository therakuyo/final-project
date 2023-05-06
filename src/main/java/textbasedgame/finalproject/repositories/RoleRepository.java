package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {

    Optional<RoleEntity> findByName(String name);

}
