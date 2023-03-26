package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.EnemyEntity;

public interface EnemyRepository extends CrudRepository<EnemyEntity, String> {
}
