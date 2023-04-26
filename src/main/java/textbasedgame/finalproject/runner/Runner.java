package textbasedgame.finalproject.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import textbasedgame.finalproject.services.CharacterService;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private CharacterService characterService;

    @Override
    public void run(String... args) throws Exception {

//        characterService.add("name", 12, "assassin");

//        characterService.delete("name");

        /*

        not needed maybe, to be deleted after confirming

         */

    }
}
