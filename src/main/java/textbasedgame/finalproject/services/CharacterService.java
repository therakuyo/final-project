package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.CharacterDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
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

        return this.characterRepository.findByName(name);

    }

    public Iterable<CharacterEntity> getAll() {

        return this.characterRepository.findAll();

    }


    @Transactional
    public CharacterEntity add(CharacterDTO characterDTO, int classId) throws NonexistentResourceException {

        CharacterEntity character = new CharacterEntity();

        character.setName(characterDTO.getName());
        character.setLevel(characterDTO.getLevel());

        Optional<ClassEntity> optionalClass = this.classRepository.findById(classId);

        if (!optionalClass.isPresent()){
            throw new NonexistentResourceException("This class doesn't exist.", classId);
        }

        character.setCharacterClass(this.classRepository.findById(classId).get());

        return this.characterRepository.save(character);

    }


    @Transactional
    public CharacterEntity changeName(int id, CharacterDTO characterDTO) throws NonexistentResourceException {

        Optional<CharacterEntity> characterOptional = this.characterRepository.findById(id);

        if (!characterOptional.isPresent()) {
            throw new NonexistentResourceException("Character doesn't exist", id);
        }

        CharacterEntity characterEntity = characterOptional.get();
        characterEntity.setName(characterDTO.getName());

        return this.characterRepository.save(characterEntity);

    }

    @Transactional
    public CharacterEntity changeClass(int id, CharacterDTO characterDTO) throws NonexistentResourceException {

        Optional<CharacterEntity> characterOptional = this.characterRepository.findById(id);

        if (!characterOptional.isPresent()) {
            throw new NonexistentResourceException("Character doesn't exist", id);
        }

        CharacterEntity characterEntity = characterOptional.get();
        characterEntity.getCharacterClass().setClassName(characterDTO.getClassName());

        return characterEntity;
        //TODO - not working as intended

    }

    public void delete(int id) {

        this.characterRepository.deleteById(id);

    }


}
