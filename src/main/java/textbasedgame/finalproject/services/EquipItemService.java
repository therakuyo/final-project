package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.exceptions.ItemAlreadyEquippedException;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.EnemyRepository;
import textbasedgame.finalproject.repositories.ItemRepository;


@Service
public class EquipItemService {

    /*

    equip weapons, armour and jewellery on character and enemy

    can have only one two-handed weapon or two one-handed weapons

    can have only 3 jewellery ( thinking about limiting to 2 "rings" 1 "amulet" )

    can have only 6 armour ( limiting to 1 of each: "helmet", "boots", "gloves", "shoulder plates", "chest piece", "belt" )

    trying to limit by name of the item: if "helmet" is found then you can't equip another helmet

     */

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private EnemyRepository enemyRepository;

    @Autowired
    private ItemRepository itemRepository;


    @Transactional
    public void equipItemOnCharacter(int characterId, int itemId)
        throws NonexistentResourceException, ItemAlreadyEquippedException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));

        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));

        if (character.getCharacterItems().contains(item)){
            throw new ItemAlreadyEquippedException("Item is already equipped", itemId);
        }

        if (item.getType().getCode() == 1){
            //todo - continue implementing
        }

        character.getCharacterItems().add(item);

        this.characterRepository.save(character);

    }



}
