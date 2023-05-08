package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.JewelleryDTO;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.JewelleryEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ItemRepository;
import textbasedgame.finalproject.repositories.JewelleryRepository;

import java.util.Optional;

@Service
public class JewelleryService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private JewelleryRepository jewelleryRepository;


    @Transactional
    public JewelleryEntity add(JewelleryDTO jewelleryDTO) {

        JewelleryEntity jewellery = new JewelleryEntity();

        jewellery.setName(jewelleryDTO.getName());

        if (jewelleryDTO.getAttackDamage() != null) {
            jewellery.setAttackDamage(jewelleryDTO.getAttackDamage());
        }

        if (jewelleryDTO.getMagicDamage() != null) {
            jewellery.setMagicDamage(jewelleryDTO.getMagicDamage());
        }

        if (jewelleryDTO.getHealthPoints() != null) {
            jewellery.setHealthPoints(jewelleryDTO.getHealthPoints());
        }

        if (jewelleryDTO.getResistance() != null) {
            jewellery.setResistance(jewelleryDTO.getResistance());
        }

        jewellery.setType(jewelleryDTO.getType());
        jewellery.setRarity(jewelleryDTO.getRarity());

        return this.itemRepository.save(jewellery);

    }


    public void delete(int id) throws NonexistentResourceException {

        JewelleryEntity jewellery = this.jewelleryRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Jewellery doesn't exist", id));

        this.itemRepository.delete(jewellery);

    }


    @Transactional
    public JewelleryEntity updateComplete(int id, JewelleryDTO jewelleryDTO) throws NonexistentResourceException {

        JewelleryEntity jewellery = this.jewelleryRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Jewellery doesn't exist", id));

        jewellery.setType(jewelleryDTO.getType());
        jewellery.setRarity(jewelleryDTO.getRarity());
        jewellery.setName(jewelleryDTO.getName());

        if (jewelleryDTO.getAttackDamage() != null) {
            jewellery.setAttackDamage(jewelleryDTO.getAttackDamage());
        }

        if (jewelleryDTO.getMagicDamage() != null) {
            jewellery.setMagicDamage(jewelleryDTO.getMagicDamage());
        }

        if (jewelleryDTO.getHealthPoints() != null) {
            jewellery.setHealthPoints(jewelleryDTO.getHealthPoints());
        }

        if (jewelleryDTO.getResistance() != null) {
            jewellery.setResistance(jewelleryDTO.getResistance());
        }

        return this.itemRepository.save(jewellery);

    }


    @Transactional
    public JewelleryEntity updatePartial(int id, JewelleryDTO jewelleryDTO) throws NonexistentResourceException {

        JewelleryEntity jewellery = this.jewelleryRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Jewellery doesn't exist", id));

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


    public Iterable<ItemEntity> getAllItems() {
        return this.itemRepository.findAll();
    }

    public Iterable<JewelleryEntity> getAllJewellery() {
        return this.jewelleryRepository.findAll();
    }

    public JewelleryEntity findById(int id) throws NonexistentResourceException {
        return this.jewelleryRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Jewellery doesn't exist", id));
    }

}
