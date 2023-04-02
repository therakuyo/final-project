package textbasedgame.finalproject.services;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.enums.Difficulty;

@Service
public class ZoneService {

    @Autowired
    private ZoneService zoneService;


    public ZoneEntity add(String name, Difficulty difficulty){

        return null;

    }

}
