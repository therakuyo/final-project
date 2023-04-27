package textbasedgame.finalproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import textbasedgame.finalproject.dtos.ZoneDTO;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.repositories.ZoneRepository;

import java.util.Optional;

@Service
public class ZoneService {

    @Autowired
    private ZoneRepository zoneRepository;


    @Transactional
    public ZoneEntity add(ZoneDTO zoneDTO) {

        ZoneEntity zone = new ZoneEntity();

        zone.setZoneName(zoneDTO.getZoneName());
        zone.setDifficulty(zoneDTO.getDifficulty());

        return this.zoneRepository.save(zone);

    }

    public void delete(int id) {

        this.zoneRepository.deleteById(id);

    }

    @Transactional
    public ZoneEntity updateComplete(int id, ZoneDTO zoneDTO) throws NonexistentResourceException {

        Optional<ZoneEntity> optionalZone = this.zoneRepository.findById(id);

        if (!optionalZone.isPresent()) {
            throw new NonexistentResourceException("This zone doesn't exist", id);
        }

        ZoneEntity zone = optionalZone.get();

        zone.setZoneName(zoneDTO.getZoneName());
        zone.setDifficulty(zoneDTO.getDifficulty());

        return this.zoneRepository.save(zone);

    }


    @Transactional
    public ZoneEntity updatePartial(int id, ZoneDTO zoneDTO) throws NonexistentResourceException {

        Optional<ZoneEntity> optionalZone = this.zoneRepository.findById(id);

        if (!optionalZone.isPresent()) {
            throw new NonexistentResourceException("This zone doesn't exist", id);
        }

        ZoneEntity zone = optionalZone.get();

        if (zoneDTO.getZoneName() != null) {
            zone.setZoneName(zoneDTO.getZoneName());
        }

        if (zoneDTO.getDifficulty() != null) {
            zone.setDifficulty(zoneDTO.getDifficulty());
        }

        return this.zoneRepository.save(zone);

    }

}
