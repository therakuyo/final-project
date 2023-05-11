package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ZoneDTO;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ZoneRepository;


@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;


    public ZoneEntity findById(int id) throws NonexistentResourceException {

        return this.zoneRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Zone doesn't exist", id));

    }

    public Iterable<ZoneEntity> getAll() {

        return this.zoneRepository.findAll();

    }

    @Transactional
    public ZoneEntity add(ZoneDTO zoneDTO) {

        ZoneEntity zone = new ZoneEntity();

        zone.setZoneName(zoneDTO.getZoneName());
        zone.setDifficulty(zoneDTO.getDifficulty());

        return this.zoneRepository.save(zone);

    }

    public void delete(int id) throws NonexistentResourceException {

        ZoneEntity zone = this.zoneRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Zone doesn't exist", id));

        this.zoneRepository.delete(zone);

    }

    @Transactional
    public ZoneEntity updateComplete(int id, ZoneDTO zoneDTO) throws NonexistentResourceException {

        ZoneEntity zone = this.zoneRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Zone doesn't exist", id));

        zone.setZoneName(zoneDTO.getZoneName());
        zone.setDifficulty(zoneDTO.getDifficulty());

        return this.zoneRepository.save(zone);

    }


    @Transactional
    public ZoneEntity updatePartial(int id, ZoneDTO zoneDTO) throws NonexistentResourceException {

        ZoneEntity zone = this.zoneRepository.findById(id)
            .orElseThrow(() -> new NonexistentResourceException("Zone doesn't exist", id));

        if (zoneDTO.getZoneName() != null) {
            zone.setZoneName(zoneDTO.getZoneName());
        }

        if (zoneDTO.getDifficulty() != null) {
            zone.setDifficulty(zoneDTO.getDifficulty());
        }

        return this.zoneRepository.save(zone);

    }

}
