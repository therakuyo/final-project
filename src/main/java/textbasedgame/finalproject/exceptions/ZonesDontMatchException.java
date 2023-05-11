package textbasedgame.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ZonesDontMatchException extends Exception{

    private String message;

    private int characterId;

    private int enemyId;

}
