package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.exceptions.NonexistentCharacterException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;


    @Transactional
    public CharacterEntity add(String name, int level, String className) {

        return null;
    }


    public List<CharacterEntity> findByLevel(int level) {
        return characterRepository.findByLevel(level);
    }

    public List<CharacterEntity> findByClass(String className) {
        return characterRepository.findByClassEntity_ClassName(className);
    }

    public void update(String name, String newName) throws NonexistentCharacterException {

//        Optional<CharacterEntity> optionalCharacter = characterRepository.findById(name);
//
//        if (!optionalCharacter.isPresent()) {
//            throw new NonexistentCharacterException("This character doesn't exist", name);
//        } else {
//            CharacterEntity updatedCharacter = optionalCharacter.get();
//            updatedCharacter.setName(newName);
//
//            this.characterRepository.save(updatedCharacter);
//        }
    }

    public void delete(String name) throws NonexistentCharacterException {

        Optional<CharacterEntity> optionalCharacter = characterRepository.findById(name);

        if (!optionalCharacter.isPresent()) {
            throw new NonexistentCharacterException("This character doesn't exist", name);
        } else {
            this.characterRepository.delete(optionalCharacter.get());
        }
    }
}
