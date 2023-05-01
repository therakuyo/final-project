package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.dtos.EnemyDTO;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.EnemyRepository;

import java.util.Optional;

@Service
public class EnemyService {

    @Autowired
    private EnemyRepository enemyRepository;


    public EnemyEntity add(EnemyDTO enemyDTO) {

        EnemyEntity enemy = new EnemyEntity();

        enemy.setName(enemyDTO.getName());

        return this.enemyRepository.save(enemy);

    }

    public EnemyEntity update(int id, EnemyDTO enemyDTO) throws NonexistentResourceException {

        Optional<EnemyEntity> enemyOptional = this.enemyRepository.findById(id);

        if (!enemyOptional.isPresent()) {

            throw new NonexistentResourceException("Enemy doesn't exist", id);

        }

        EnemyEntity enemy = enemyOptional.get();
        enemy.setName(enemyDTO.getName());

        return this.enemyRepository.save(enemy);

    }

    public void delete(int id) {

        this.enemyRepository.deleteById(id);

    }

}
