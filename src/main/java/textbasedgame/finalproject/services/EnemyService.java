package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.dtos.EnemyDTO;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.EnemyRepository;


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

        EnemyEntity enemy = this.enemyRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", id));

        enemy.setName(enemyDTO.getName());

        return this.enemyRepository.save(enemy);

    }

    public void delete(int id) throws NonexistentResourceException {

        EnemyEntity enemy = this.enemyRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", id));

        this.enemyRepository.delete(enemy);

    }

    public EnemyEntity findById(int id) throws NonexistentResourceException {
        return this.enemyRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", id));
    }

    public Iterable<EnemyEntity> getAll() {

        return this.enemyRepository.findAll();

    }

}
