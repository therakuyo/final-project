package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.WeaponEntity;

public interface WeaponRepository extends CrudRepository<WeaponEntity, Integer> {
}
