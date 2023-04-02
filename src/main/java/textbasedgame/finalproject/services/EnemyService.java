package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.repositories.EnemyRepository;

@Service
public class EnemyService {

    @Autowired
    private EnemyRepository enemyRepository;


    public EnemyEntity add(String name){
        return null;
    }

}
