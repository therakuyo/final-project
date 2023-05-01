package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ArmorDTO;
import textbasedgame.finalproject.dtos.JewelleryDTO;
import textbasedgame.finalproject.dtos.WeaponDTO;
import textbasedgame.finalproject.entities.ArmorEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.JewelleryEntity;
import textbasedgame.finalproject.entities.WeaponEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ArmorRepository;
import textbasedgame.finalproject.repositories.ItemRepository;
import textbasedgame.finalproject.repositories.JewelleryRepository;
import textbasedgame.finalproject.repositories.WeaponRepository;

import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ArmorRepository armorRepository;

    @Autowired
    private JewelleryRepository jewelleryRepository;

    @Autowired
    private WeaponRepository weaponRepository;


    @Transactional
    public ArmorEntity add(ArmorDTO armorDTO) {

        ArmorEntity armor = new ArmorEntity();

        armor.setName(armorDTO.getName());
        armor.setHealthPoints(armorDTO.getHealthPoints());
        armor.setResistance(armorDTO.getResistance());
        armor.setType(armorDTO.getType());
        armor.setRarity(armorDTO.getRarity());

        return this.itemRepository.save(armor);

    }


    @Transactional
    public WeaponEntity add(WeaponDTO weaponDTO) {

        WeaponEntity weapon = new WeaponEntity();

        weapon.setName(weaponDTO.getName());
        weapon.setAttackDamage(weaponDTO.getAttackDamage());
        weapon.setRequirements(weaponDTO.getRequirements());
        weapon.setType(weaponDTO.getType());
        weapon.setRarity(weaponDTO.getRarity());

        return this.itemRepository.save(weapon);

    }


    @Transactional
    public JewelleryEntity add(JewelleryDTO jewelleryDTO) {

        JewelleryEntity jewellery = new JewelleryEntity();

        jewellery.setName(jewelleryDTO.getName());
        jewellery.setAttackDamage(jewelleryDTO.getAttackDamage());
        jewellery.setMagicDamage(jewelleryDTO.getMagicDamage());
        jewellery.setHealthPoints(jewelleryDTO.getHealthPoints());
        jewellery.setResistance(jewelleryDTO.getResistance());
        jewellery.setType(jewelleryDTO.getType());
        jewellery.setRarity(jewelleryDTO.getRarity());

        return this.itemRepository.save(jewellery);

    }


    public void delete(int id) throws NonexistentResourceException {

        Optional<ItemEntity> itemOptional = this.itemRepository.findById(id);

        if (!itemOptional.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        this.itemRepository.delete(itemOptional.get());

    }

    //    public ItemEntity updateComplete(int id, ItemDTO itemD) throws NonexistentResourceException {
    //
    //        Optional<ItemEntity> optionalItem = this.itemRepository.findById(id);
    //
    //        String foundItemType = optionalItem.get().getType();
    //
    //        if (!optionalItem.isPresent()){
    //            throw new NonexistentResourceException("This item doesn't exist", id);
    //        } else {
    //            if (foundItemType.equals("weapon")){
    //
    //                ItemEntity item = optionalItem.get();
    //
    //                item.setRarity(itemD.getRarity());
    //                item.setType(itemD.getType());
    //
    //                WeaponEntity weapon = optionalItem.get();
    //
    //            }
    //        }
    //
    //    }

    @Transactional
    public ArmorEntity updateComplete(int id, ArmorDTO armorDTO) throws NonexistentResourceException {

        Optional<ArmorEntity> optionalArmor = this.armorRepository.findById(id);

        if (!optionalArmor.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        ArmorEntity armor = optionalArmor.get();

        armor.setType(armorDTO.getType());
        armor.setRarity(armorDTO.getRarity());
        armor.setName(armorDTO.getName());
        armor.setHealthPoints(armorDTO.getHealthPoints());
        armor.setResistance(armorDTO.getResistance());

        return this.itemRepository.save(armor);

    }


    @Transactional
    public WeaponEntity updateComplete(int id, WeaponDTO weaponDTO) throws NonexistentResourceException {

        Optional<WeaponEntity> optionalWeapon = this.weaponRepository.findById(id);

        if (!optionalWeapon.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        WeaponEntity weapon = optionalWeapon.get();

        weapon.setType(weaponDTO.getType());
        weapon.setRarity(weaponDTO.getRarity());
        weapon.setName(weaponDTO.getName());
        weapon.setAttackDamage(weaponDTO.getAttackDamage());
        weapon.setRequirements(weaponDTO.getRequirements());

        return this.itemRepository.save(weapon);

    }


    @Transactional
    public JewelleryEntity updateComplete(int id, JewelleryDTO jewelleryDTO) throws NonexistentResourceException {

        Optional<JewelleryEntity> optionalJewellery = this.jewelleryRepository.findById(id);

        if (!optionalJewellery.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        JewelleryEntity jewellery = optionalJewellery.get();

        jewellery.setType(jewelleryDTO.getType());
        jewellery.setRarity(jewelleryDTO.getRarity());
        jewellery.setName(jewelleryDTO.getName());
        jewellery.setHealthPoints(jewelleryDTO.getHealthPoints());
        jewellery.setResistance(jewelleryDTO.getResistance());
        jewellery.setAttackDamage(jewelleryDTO.getAttackDamage());
        jewellery.setMagicDamage(jewelleryDTO.getMagicDamage());

        return this.itemRepository.save(jewellery);

    }


    @Transactional
    public ArmorEntity updatePartial(int id, ArmorDTO armorDTO) throws NonexistentResourceException {

        Optional<ArmorEntity> optionalArmor = this.armorRepository.findById(id);

        if (!optionalArmor.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        ArmorEntity armor = optionalArmor.get();

        if (armorDTO.getName() != null) {
            armor.setName(armorDTO.getName());
        }

        if (armorDTO.getType() != null) {
            armor.setType(armorDTO.getType());
        }

        if (armorDTO.getRarity() != null) {
            armor.setRarity(armorDTO.getRarity());
        }

        if (armorDTO.getResistance() != null) {
            armor.setResistance(armorDTO.getResistance());
        }

        if (armorDTO.getHealthPoints() != null) {
            armor.setHealthPoints(armorDTO.getHealthPoints());
        }

        return this.itemRepository.save(armor);

    }


    @Transactional
    public WeaponEntity updatePartial(int id, WeaponDTO weaponDTO) throws NonexistentResourceException {

        Optional<WeaponEntity> optionalWeapon = this.weaponRepository.findById(id);

        if (!optionalWeapon.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        WeaponEntity weapon = optionalWeapon.get();

        if (weaponDTO.getName() != null) {
            weapon.setName(weaponDTO.getName());
        }

        if (weaponDTO.getType() != null) {
            weapon.setType(weaponDTO.getType());
        }

        if (weaponDTO.getRarity() != null) {
            weapon.setRarity(weaponDTO.getRarity());
        }

        if (weaponDTO.getRequirements() != null) {
            weapon.setRequirements(weaponDTO.getRequirements());
        }

        if (weaponDTO.getAttackDamage() != null) {
            weapon.setAttackDamage(weaponDTO.getAttackDamage());
        }

        return this.itemRepository.save(weapon);

    }


    @Transactional
    public JewelleryEntity updatePartial(int id, JewelleryDTO jewelleryDTO) throws NonexistentResourceException {

        Optional<JewelleryEntity> optionalJewellery = this.jewelleryRepository.findById(id);

        if (!optionalJewellery.isPresent()) {
            throw new NonexistentResourceException("This item doesn't exist", id);
        }

        JewelleryEntity jewellery = optionalJewellery.get();

        if (jewelleryDTO.getName() != null) {
            jewellery.setName(jewelleryDTO.getName());
        }

        if (jewelleryDTO.getType() != null) {
            jewellery.setType(jewelleryDTO.getType());
        }

        if (jewelleryDTO.getRarity() != null) {
            jewellery.setRarity(jewelleryDTO.getRarity());
        }

        if (jewelleryDTO.getMagicDamage() != null) {
            jewellery.setMagicDamage(jewelleryDTO.getMagicDamage());
        }

        if (jewelleryDTO.getAttackDamage() != null) {
            jewellery.setAttackDamage(jewelleryDTO.getAttackDamage());
        }

        if (jewelleryDTO.getHealthPoints() != null) {
            jewellery.setHealthPoints(jewelleryDTO.getHealthPoints());
        }

        if (jewelleryDTO.getResistance() != null) {
            jewellery.setResistance(jewelleryDTO.getResistance());
        }

        return this.itemRepository.save(jewellery);

    }

    public Iterable<ItemEntity> findAllItems() {
        return this.itemRepository.findAll();
    }

    public Iterable<ArmorEntity> findAllArmor() {
        return this.armorRepository.findAll();
    }

    public Iterable<WeaponEntity> findAllWeapons() {
        return this.weaponRepository.findAll();
    }

    public Iterable<JewelleryEntity> findAllJewellery() {
        return this.jewelleryRepository.findAll();
    }

}
