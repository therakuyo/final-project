package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.exceptions.NotEnoughExperiencePointsToLevelUp;
import textbasedgame.finalproject.repositories.CharacterRepository;

@Service
public class LevelUpService {

    @Autowired
    private CharacterRepository characterRepository;


    @Transactional
    public void levelUp(int characterId) throws NonexistentResourceException, NotEnoughExperiencePointsToLevelUp {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        if (character.getExperiencePoints() <= checkLevelUpRequirement(characterId)) {

            throw new NotEnoughExperiencePointsToLevelUp("You need more experience points to level up");
        }

        character.setExperiencePoints(character.getExperiencePoints() - checkLevelUpRequirement(characterId));
        character.setLevel(character.getLevel() + 1);

        this.characterRepository.save(character);

    }


    public void checkProgress(int characterId) throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        System.out.println(character.getExperiencePoints() + "/" + checkLevelUpRequirement(characterId));

    }


    public int checkLevelUpRequirement(int characterId) throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        int requirement = 100;

        for (int i = 0; i <= 25; i++) {

            i++;
            requirement += 50;

            if (i == character.getLevel()) {
                break;
            }

        }

        return requirement;

    }

}
