package textbasedgame.finalproject.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NotEnoughGoldToBuyItemException extends Exception{

    private String message;

    private int price;

}
