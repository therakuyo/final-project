package textbasedgame.finalproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ItemType {

    ONE_HANDED_WEAPON(1),

    TWO_HANDED_WEAPON(2),

    RING(3),

    AMULET(4),

    HELMET(5),

    BOOTS(6),

    GLOVES(7),

    SHOULDER_PLATES(8),

    CHEST_PIECE(9),

    BELT(10);

    private final int code;

}
