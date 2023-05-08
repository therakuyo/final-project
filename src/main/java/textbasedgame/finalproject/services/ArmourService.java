package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ArmourDTO;
import textbasedgame.finalproject.entities.ArmourEntity;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ArmourRepository;
import textbasedgame.finalproject.repositories.ItemRepository;


@Service
public class ArmourService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ArmourRepository armourRepository;


    @Transactional
    public ArmourEntity add(ArmourDTO armourDTO) {

        ArmourEntity armour = new ArmourEntity();

        armour.setName(armourDTO.getName());
        armour.setHealthPoints(armourDTO.getHealthPoints());
        armour.setResistance(armourDTO.getResistance());
        armour.setType(armourDTO.getType());
        armour.setRarity(armourDTO.getRarity());

        return this.itemRepository.save(armour);

    }


    public void delete(int id) throws NonexistentResourceException {

        ArmourEntity armour = this.armourRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Armour doesn't exist", id));

        this.itemRepository.delete(armour);

    }


    @Transactional
    public ArmourEntity updateComplete(int id, ArmourDTO armourDTO) throws NonexistentResourceException {

        ArmourEntity armour = this.armourRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Armour doesn't exist", id));

        armour.setType(armourDTO.getType());
        armour.setRarity(armourDTO.getRarity());
        armour.setName(armourDTO.getName());
        armour.setHealthPoints(armourDTO.getHealthPoints());
        armour.setResistance(armourDTO.getResistance());

        return this.itemRepository.save(armour);

    }


    @Transactional
    public ArmourEntity updatePartial(int id, ArmourDTO armourDTO) throws NonexistentResourceException {

        ArmourEntity armour = this.armourRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Armour doesn't exist", id));

        if (armourDTO.getName() != null) {
            armour.setName(armourDTO.getName());
        }

        if (armourDTO.getType() != null) {
            armour.setType(armourDTO.getType());
        }

        if (armourDTO.getRarity() != null) {
            armour.setRarity(armourDTO.getRarity());
        }

        if (armourDTO.getResistance() != null) {
            armour.setResistance(armourDTO.getResistance());
        }

        if (armourDTO.getHealthPoints() != null) {
            armour.setHealthPoints(armourDTO.getHealthPoints());
        }

        return this.itemRepository.save(armour);

    }


    public Iterable<ItemEntity> getAllItems() {
        return this.itemRepository.findAll();
    }

    public Iterable<ArmourEntity> getAllArmour() {
        return this.armourRepository.findAll();
    }

    public ArmourEntity findById(int id) throws NonexistentResourceException {
        return this.armourRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Armour doesn't exist", id));
    }

}
