package textbasedgame.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.enums.ItemType;

@Getter
@Setter
@AllArgsConstructor
public class MaxOfItemTypeEquippedExceeded extends Exception{

    private String message;

    private ItemType itemType;

}
