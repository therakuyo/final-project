package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.CharacterEntity;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends CrudRepository<CharacterEntity, Integer> {

    List<CharacterEntity> findByLevel(int level);

    Optional<CharacterEntity> findByName(String name);

}
