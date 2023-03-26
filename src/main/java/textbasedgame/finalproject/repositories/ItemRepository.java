package textbasedgame.finalproject.repositories;

import org.springframework.data.repository.CrudRepository;
import textbasedgame.finalproject.entities.ItemEntity;

public interface ItemRepository extends CrudRepository<ItemEntity, String> {
}
