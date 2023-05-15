package textbasedgame.finalproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    UNEQUIPPED(1),

    EQUIPPED(2);

    private final int code;

}
