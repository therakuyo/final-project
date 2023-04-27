package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.CharacterDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
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


    public List<CharacterEntity> findByLevel(int level) {
        return this.characterRepository.findByLevel(level);
    }

//    public List<CharacterEntity> findByClass(String className) {
//        return this.characterRepository.findByClassEntity_ClassName(className);
//    }

    public Optional<CharacterEntity> findByName(String name) {

        return this.characterRepository.findById(name);

    }

    public Iterable<CharacterEntity> getAll() {

        return this.characterRepository.findAll();

    }


    @Transactional
    public CharacterEntity add(CharacterDTO characterDTO, int classId) {

        CharacterEntity character = new CharacterEntity();

        character.setName(characterDTO.getName());
        character.setLevel(characterDTO.getLevel());
        character.setCharacterClass(this.classRepository.findById(classId).get());

        //TODO - exceptie daca nu exista clasa

        return this.characterRepository.save(character);

    }


    @Transactional
    public CharacterEntity changeName(String name, CharacterDTO characterDTO) throws NonexistentCharacterException {

        Optional<CharacterEntity> characterOptional = this.characterRepository.findById(name);

        if (!characterOptional.isPresent()) {
            throw new NonexistentCharacterException("Character doesn't exist", name);
        }

        CharacterEntity characterEntity = characterOptional.get();
        characterEntity.setName(characterDTO.getName());

        return this.characterRepository.save(characterEntity);

        //make pk id - integer

    }

    @Transactional
    public CharacterEntity changeClass(String name, CharacterDTO characterDTO) throws NonexistentCharacterException {

        Optional<CharacterEntity> characterOptional = this.characterRepository.findById(name);

        if (!characterOptional.isPresent()) {
            throw new NonexistentCharacterException("Character doesn't exist", name);
        }

        CharacterEntity characterEntity = characterOptional.get();
        characterEntity.getCharacterClass().setClassName(characterDTO.getClassName());

        return characterEntity;
        //TODO - not working as intended

    }

    public void delete(String name) {

        this.characterRepository.deleteById(name);

    }


}
