package textbasedgame.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotEnoughExperiencePointsToLevelUp extends Exception{

    private String message;

}
