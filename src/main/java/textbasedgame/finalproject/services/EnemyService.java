package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.exceptions.NonexistentCharacterException;
import textbasedgame.finalproject.repositories.EnemyRepository;

import java.util.Optional;

@Service
public class EnemyService {

    @Autowired
    private EnemyRepository enemyRepository;


    public EnemyEntity add(String name){

        EnemyEntity enemy = new EnemyEntity();

        enemy.setName(name);

        return this.enemyRepository.save(enemy);

    }

    public EnemyEntity update(String name) throws NonexistentCharacterException {

        Optional<EnemyEntity> enemyOptional = this.enemyRepository.findById(name);

        if (!enemyOptional.isPresent()){

            throw new NonexistentCharacterException("Enemy doesn't exist", name);

        }

        EnemyEntity enemy = enemyOptional.get();
        enemy.setName(name);

        return this.enemyRepository.save(enemy);

        //make pk id - int

    }

    public void delete(String name){

        this.enemyRepository.deleteById(name);

    }

}
