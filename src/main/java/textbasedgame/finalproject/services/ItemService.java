package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.ArmorEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.JewelleryEntity;
import textbasedgame.finalproject.entities.WeaponEntity;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Requirements;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ItemRepository;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public ArmorEntity addArmor(String name, int healthPoint, int resistance, String type, Rarity rarity){

        ArmorEntity armor = new ArmorEntity();
        armor.setName(name);
        armor.setHealthPoints(healthPoint);
        armor.setResistance(resistance);
        armor.setType(type);
        armor.setRarity(rarity);

        return this.itemRepository.save(armor);

    }


    public WeaponEntity addWeapon(String name, int attackDamage, Requirements requirements, String type, Rarity rarity){

        WeaponEntity weapon = new WeaponEntity();
        weapon.setName(name);
        weapon.setAttackDamage(attackDamage);
        weapon.setRequirements(requirements);
        weapon.setType(type);
        weapon.setRarity(rarity);

        return this.itemRepository.save(weapon);

    }


    public JewelleryEntity addJewellery(String name, int attackDamage, int magicDamage, int healthPoints, int resistance, String type, Rarity rarity){

        JewelleryEntity jewellery = new JewelleryEntity();
        jewellery.setName(name);
        jewellery.setAttackDamage(attackDamage);
        jewellery.setMagicDamage(magicDamage);
        jewellery.setHealthPoints(healthPoints);
        jewellery.setResistance(resistance);
        jewellery.setType(type);
        jewellery.setRarity(rarity);

        return this.itemRepository.save(jewellery);

    }


    public void delete(int id) throws NonexistentResourceException {

        Optional<ItemEntity> foundItem = this.itemRepository.findById(id);

        if (!foundItem.isPresent()){
            throw new NonexistentResourceException("This item doesn't exist", id);
        } else {
            this.itemRepository.delete(foundItem.get());
        }

    }

}
