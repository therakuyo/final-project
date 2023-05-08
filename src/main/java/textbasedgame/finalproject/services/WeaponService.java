package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.WeaponDTO;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.WeaponEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ItemRepository;
import textbasedgame.finalproject.repositories.WeaponRepository;


@Service
public class WeaponService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private WeaponRepository weaponRepository;


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


    public void delete(int id) throws NonexistentResourceException {

        WeaponEntity weapon = this.weaponRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Weapon doesn't exist", id));

        this.itemRepository.delete(weapon);

    }


    @Transactional
    public WeaponEntity updateComplete(int id, WeaponDTO weaponDTO) throws NonexistentResourceException {

        WeaponEntity weapon = this.weaponRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Weapon doesn't exist", id));

        weapon.setType(weaponDTO.getType());
        weapon.setRarity(weaponDTO.getRarity());
        weapon.setName(weaponDTO.getName());
        weapon.setAttackDamage(weaponDTO.getAttackDamage());
        weapon.setRequirements(weaponDTO.getRequirements());

        return this.itemRepository.save(weapon);

    }


    @Transactional
    public WeaponEntity updatePartial(int id, WeaponDTO weaponDTO) throws NonexistentResourceException {

        WeaponEntity weapon = this.weaponRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Weapon doesn't exist", id));

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


    public Iterable<ItemEntity> getAllItems() {
        return this.itemRepository.findAll();
    }

    public Iterable<WeaponEntity> getAllWeapons() {
        return this.weaponRepository.findAll();
    }

    public WeaponEntity findById(int id) throws NonexistentResourceException {
        return this.weaponRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Weapon doesn't exist", id));
    }

}
