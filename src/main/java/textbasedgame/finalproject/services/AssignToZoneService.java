package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.EnemyRepository;
import textbasedgame.finalproject.repositories.ZoneRepository;


@Service
public class AssignToZoneService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ZoneRepository zoneRepository;


    @Transactional
    public void assignCharacter(int characterId, int zoneId)
        throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("This character doesn't exist", characterId));

        ZoneEntity zone = this.zoneRepository.findById(zoneId)
            .orElseThrow(() -> new NonexistentResourceException("This zone doesn't exist", zoneId));

        character.getCharacterZones().add(zone);

        this.characterRepository.save(character);

    }


    @Transactional
    public void assignEnemy(int enemyId, int zoneId)
        throws NonexistentResourceException {

        EnemyEntity enemy = this.enemyRepository.findById(enemyId)
            .orElseThrow(() -> new NonexistentResourceException("This character doesn't exist", enemyId));

        ZoneEntity zone = this.zoneRepository.findById(zoneId)
            .orElseThrow(() -> new NonexistentResourceException("This zone doesn't exist", zoneId));

        enemy.getEnemyZones().add(zone);

        this.enemyRepository.save(enemy);

    }

}
