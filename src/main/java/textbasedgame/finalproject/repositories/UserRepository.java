package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.UserEntity;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

}
