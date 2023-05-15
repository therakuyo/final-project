package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.enums.Status;
import textbasedgame.finalproject.exceptions.ItemAlreadyEquippedException;
import textbasedgame.finalproject.exceptions.ItemIsNotEquippedException;
import textbasedgame.finalproject.exceptions.MaxOfItemTypeEquippedExceeded;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.CharacterRepository;
import textbasedgame.finalproject.repositories.EnemyRepository;
import textbasedgame.finalproject.repositories.ItemRepository;



@Service
public class EquipItemService {

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


        if (item.getStatus().getCode() == 2) {
            throw new ItemAlreadyEquippedException("Item is already equipped", itemId);
        }


        if (item.getType().getCode() == 1 || item.getType().getCode() == 3) {

            checkIfCanEquipMore(character, item.getType().getCode());

        } else {

            checkIfCanEquip(character, item.getType().getCode());
        }

        character.getCharacterItems().add(item);
        item.setStatus(Status.EQUIPPED);

        this.characterRepository.save(character);
        this.itemRepository.save(item);

    }

    @Transactional
    public void unequipItemFromCharacter(int characterId, int itemId)
        throws NonexistentResourceException, ItemIsNotEquippedException {

        CharacterEntity character = this.characterRepository.findById(characterId)
            .orElseThrow(() -> new NonexistentResourceException("Character doesn't exist", characterId));


        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));


        if (item.getStatus().getCode() == 1) {
            throw new ItemIsNotEquippedException("Item is not equipped", itemId);
        }

        character.getCharacterItems().remove(item);
        item.setStatus(Status.UNEQUIPPED);

        this.characterRepository.save(character);
        this.itemRepository.save(item);

    }



    @Transactional
    public void equipItemOnEnemy(int enemyId, int itemId)
        throws NonexistentResourceException, ItemAlreadyEquippedException, MaxOfItemTypeEquippedExceeded {

        EnemyEntity enemy = this.enemyRepository.findById(enemyId)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", enemyId));


        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));


        if (item.getStatus().getCode() == 2) {
            throw new ItemAlreadyEquippedException("Item is already equipped", itemId);
        }


        if (item.getType().getCode() == 1 || item.getType().getCode() == 3) {

            checkIfCanEquipMore(enemy, item.getType().getCode());

        } else {

            checkIfCanEquip(enemy, item.getType().getCode());
        }

        enemy.getEnemyItems().add(item);
        item.setStatus(Status.EQUIPPED);

        this.enemyRepository.save(enemy);
        this.itemRepository.save(item);

    }

    @Transactional
    public void unequipItemFromEnemy(int enemyId, int itemId)
        throws NonexistentResourceException, ItemIsNotEquippedException {

        EnemyEntity enemy = this.enemyRepository.findById(enemyId)
            .orElseThrow(() -> new NonexistentResourceException("Enemy doesn't exist", enemyId));


        ItemEntity item = this.itemRepository.findById(itemId)
            .orElseThrow(() -> new NonexistentResourceException("Item doesn't exist", itemId));


        if (item.getStatus().getCode() == 1) {
            throw new ItemIsNotEquippedException("Item is not equipped", itemId);
        }

        enemy.getEnemyItems().remove(item);
        item.setStatus(Status.UNEQUIPPED);

        this.enemyRepository.save(enemy);
        this.itemRepository.save(item);

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
