package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.CharacterDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.exceptions.CharacterAlreadyExistsException;
import textbasedgame.finalproject.exceptions.NonexistentCharacterException;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.exceptions.NotEnoughGoldToBuyItemException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.ClassRepository;
import textbasedgame.finalproject.repositories.ItemRepository;
import textbasedgame.finalproject.repositories.ShopRepository;

import java.util.List;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ShopRepository shopRepository;


    public List<CharacterEntity> findByLevel(int level) {
        return this.characterRepository.findByLevel(level);
    }


    public CharacterEntity findByName(String name) throws NonexistentCharacterException {

        return this.characterRepository.findByName(name)
            .orElseThrow(() -> new NonexistentCharacterException("Character doesn't exist", name));

    }

    public CharacterEntity findById(int id) throws NonexistentResourceException {
        return this.characterRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", id));
    }

    public Iterable<CharacterEntity> getAll() {

        return this.characterRepository.findAll();

    }


    @Transactional
    public CharacterEntity add(CharacterDTO characterDTO, int classId)
        throws NonexistentResourceException, CharacterAlreadyExistsException {

        if (this.characterRepository.existsByName(characterDTO.getName())) {

            throw new CharacterAlreadyExistsException("Character already exists", characterDTO.getName());
        }

        CharacterEntity character = new CharacterEntity();

        character.setName(characterDTO.getName());
        character.setLevel(characterDTO.getLevel());

        ClassEntity foundClass = this.classRepository.findById(classId)
            .orElseThrow(() -> new NonexistentResourceException("Class doesn't exist.", classId));

        character.setCharacterClass(foundClass);

        return this.characterRepository.save(character);

    }


    @Transactional
    public CharacterEntity changeName(int id, CharacterDTO characterDTO) throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", id));

        character.setName(characterDTO.getName());

        return this.characterRepository.save(character);

    }


    @Transactional
    public void delete(int id) throws NonexistentResourceException {

        CharacterEntity character = this.characterRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", id));

        this.characterRepository.delete(character);

    }


    @Transactional
    public void buyItem(int characterId, int itemId)
        throws NonexistentResourceException, NotEnoughGoldToBuyItemException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));

        if (item.getPrice() == 0){
            throw new NonexistentResourceException("Item hasn't been added to shop yet", itemId);
        }

        if (character.getGoldCoins() < item.getPrice()){
            throw new NotEnoughGoldToBuyItemException("More gold needed to buy this item", item.getPrice());
        }

        character.getCharacterItems().add(item);
        character.setGoldCoins(character.getGoldCoins() - item.getPrice());

        item.setPrice(0);

        this.characterRepository.save(character);
        this.itemRepository.save(item);

    }


}
