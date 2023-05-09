package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.exceptions.ItemAlreadyEquippedException;
import textbasedgame.finalproject.exceptions.MaxOfItemTypeEquippedExceeded;
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
        throws NonexistentResourceException, ItemAlreadyEquippedException, MaxOfItemTypeEquippedExceeded {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));


        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));


        if (character.getCharacterItems().contains(item)) {
            throw new ItemAlreadyEquippedException("Item is already equipped", itemId);
        }


        if (item.getType().getCode() == 1 || item.getType().getCode() == 3) {

            checkIfCanEquipMore(character, item.getType().getCode());

        } else {

            checkIfCanEquip(character, item.getType().getCode());
        }

        character.getCharacterItems().add(item);

        this.characterRepository.save(character);

    }



    @Transactional
    public void equipItemOnEnemy(int enemyId, int itemId)
        throws NonexistentResourceException, ItemAlreadyEquippedException, MaxOfItemTypeEquippedExceeded {

        EnemyEntity enemy = this.enemyRepository.findById(enemyId)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", enemyId));


        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));


        if (enemy.getEnemyItems().contains(item)) {
            throw new ItemAlreadyEquippedException("Item is already equipped", itemId);
        }


        if (item.getType().getCode() == 1 || item.getType().getCode() == 3) {

            checkIfCanEquipMore(enemy, item.getType().getCode());

        } else {

            checkIfCanEquip(enemy, item.getType().getCode());
        }

        enemy.getEnemyItems().add(item);

        this.enemyRepository.save(enemy);

    }



    public void checkIfCanEquip(CharacterEntity character, int typeCode)
        throws MaxOfItemTypeEquippedExceeded {

        for (ItemEntity itemEntity : character.getCharacterItems()) {

            if (itemEntity.getType().getCode() == typeCode) {

                throw new MaxOfItemTypeEquippedExceeded("Can't equip an item of this type", itemEntity.getType());
            }
        }
    }

    public void checkIfCanEquip(EnemyEntity enemy, int typeCode)
        throws MaxOfItemTypeEquippedExceeded {

        for (ItemEntity itemEntity : enemy.getEnemyItems()) {

            if (itemEntity.getType().getCode() == typeCode) {

                throw new MaxOfItemTypeEquippedExceeded("Can't equip an item of this type", itemEntity.getType());
            }
        }
    }


    public void checkIfCanEquipMore(CharacterEntity character, int typeCode) throws MaxOfItemTypeEquippedExceeded {

        int typeOfItemAlreadyEquipped = 0;

        for (ItemEntity itemEntity : character.getCharacterItems()) {

            if (itemEntity.getType().getCode() == typeCode) {

                typeOfItemAlreadyEquipped++;

                if (typeOfItemAlreadyEquipped == 2) {

                    throw new MaxOfItemTypeEquippedExceeded("Can't equip an item of this type", itemEntity.getType());
                }
            }
        }
    }

    public void checkIfCanEquipMore(EnemyEntity enemy, int typeCode) throws MaxOfItemTypeEquippedExceeded {

        int typeOfItemAlreadyEquipped = 0;

        for (ItemEntity itemEntity : enemy.getEnemyItems()) {

            if (itemEntity.getType().getCode() == typeCode) {

                typeOfItemAlreadyEquipped++;

                if (typeOfItemAlreadyEquipped == 2) {

                    throw new MaxOfItemTypeEquippedExceeded("Can't equip an item of this type", itemEntity.getType());
                }
            }
        }
    }
}
