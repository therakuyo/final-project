package textbasedgame.finalproject.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import textbasedgame.finalproject.services.CharacterService;

@Component
public class Application implements CommandLineRunner {

    @Autowired
    private CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {


    }
}
