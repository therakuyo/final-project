package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.EnemyRepository;
import textbasedgame.finalproject.repositories.ZoneRepository;

import java.util.Optional;

@Service
public class AssignToZoneService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ZoneRepository zoneRepository;


    public void assignCharacter(int characterId, int zoneId)
        throws NonexistentResourceException {

        Optional<CharacterEntity> optionalCharacter = this.characterRepository.findById(characterId);

        if (!optionalCharacter.isPresent()) {
            throw new NonexistentResourceException("This character doesn't exist", characterId);
        }

        Optional<ZoneEntity> optionalZone = this.zoneRepository.findById(zoneId);

        if (!optionalZone.isPresent()) {
            throw new NonexistentResourceException("This zone doesn't exist", zoneId);
        }

        CharacterEntity character = optionalCharacter.get();
        ZoneEntity zone = optionalZone.get();

        character.getCharacterZones().add(zone);

        this.characterRepository.save(character);

    }


    public void assignEnemy(int enemyId, int zoneId)
        throws NonexistentResourceException {

        Optional<EnemyEntity> optionalEnemy = this.enemyRepository.findById(enemyId);

        if (!optionalEnemy.isPresent()) {
            throw new NonexistentResourceException("This character doesn't exist", enemyId);
        }

        Optional<ZoneEntity> optionalZone = this.zoneRepository.findById(zoneId);

        if (!optionalZone.isPresent()) {
            throw new NonexistentResourceException("This zone doesn't exist", zoneId);
        }

        EnemyEntity enemy = optionalEnemy.get();
        ZoneEntity zone = optionalZone.get();

        enemy.getEnemyZones().add(zone);

        this.enemyRepository.save(enemy);

    }

}
