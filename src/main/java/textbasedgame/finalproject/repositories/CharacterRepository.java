package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ClassEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CharacterRepository extends CrudRepository<CharacterEntity, Integer> {

    List<CharacterEntity> findByLevel(int level);

//    List<CharacterEntity> findByClassEntity_ClassName(String name);

    Optional<CharacterEntity> findByName(String name);

}
