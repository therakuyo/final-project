package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.*;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.exceptions.ZonesDontMatchException;
import textbasedgame.finalproject.repositories.*;

import java.util.Set;

@Service
public class FightService {

    /*

    a character can fight an enemy if they are in the same zone

    if power level of character > enemy -> character wins and gains xp

    ( thinking of adding xp field on character, needs 100 experience points to go from lvl 1 to lvl 2, and the requirement goes up every level,
      maybe limit zones by level -> easy = level 1, medium = level 5, hard = level 10, extreme = level 15 )

     */

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ArmourRepository armourRepository;

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private JewelleryRepository jewelleryRepository;


    @Transactional
    public void fight(int characterId, int enemyId) throws NonexistentResourceException, ZonesDontMatchException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        EnemyEntity enemy = this.enemyRepository.findById(enemyId)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", enemyId));

        if (!character.getCharacterZones().equals(enemy.getEnemyZones())) {

            throw new ZonesDontMatchException("Different zones. Can't fight", characterId, enemyId);
        }

        calculateCharacterPowerLevel(characterId);
        calculateEnemyPowerLevel(enemyId);

        if (character.getPowerLevel() < enemy.getPowerLevel()) {
            System.out.println("You died!");
        }

        if (character.getPowerLevel() == enemy.getPowerLevel()) {
            System.out.println("Draw");
        }

        if (character.getPowerLevel() > enemy.getPowerLevel()) {
            System.out.println("You win!");

            character.setExperiencePoints(character.getExperiencePoints() + enemy.getGivesXp());

            character.setGoldCoins(character.getGoldCoins() + enemy.getGivesGoldCoins());

            this.characterRepository.save(character);

        }

    }


    @Transactional
    public void calculateCharacterPowerLevel(int id) throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", id));

        int powerLevel = 0;

        for (ItemEntity item : character.getCharacterItems()) {

            if (item.getStatus().getCode() == 2) {

                powerLevel += extractItemValues(item.getId());
            }

        }

        character.setPowerLevel(powerLevel);

        this.characterRepository.save(character);

    }


    @Transactional
    public void calculateEnemyPowerLevel(int id) throws NonexistentResourceException {

        EnemyEntity enemy = this.enemyRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", id));

        int powerLevel = 0;

        for (ItemEntity item : enemy.getEnemyItems()) {

            powerLevel += extractItemValues(item.getId());

        }

        enemy.setPowerLevel(powerLevel);

        this.enemyRepository.save(enemy);

    }


    @Transactional
    public int extractItemValues(int itemId) {

        int itemValue = 0;

        if (this.armourRepository.findById(itemId).isPresent()) {

            itemValue +=
                this.armourRepository.findById(itemId).get().getHealthPoints() + this.armourRepository.findById(itemId)
                    .get()
                    .getResistance();

        }

        if (this.weaponRepository.findById(itemId).isPresent()) {

            itemValue += this.weaponRepository.findById(itemId).get().getAttackDamage() * 2;

        }

        if (this.jewelleryRepository.findById(itemId).isPresent()) {

            itemValue +=
                this.jewelleryRepository.findById(itemId).get().getMagicDamage() * 2
                    + this.jewelleryRepository.findById(
                    itemId).get().getAttackDamage() * 2 + this.jewelleryRepository.findById(itemId)
                    .get()
                    .getHealthPoints()
                    + this.jewelleryRepository.findById(itemId).get().getResistance();

        }

        return itemValue;

    }

}
