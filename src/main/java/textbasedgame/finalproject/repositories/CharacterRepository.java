package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ClassEntity;

import java.util.List;

public interface CharacterRepository extends CrudRepository<CharacterEntity, String> {

    List<CharacterEntity> findByLevel(int level);

//    List<CharacterEntity> findByClassEntity_ClassName(String name);
}
