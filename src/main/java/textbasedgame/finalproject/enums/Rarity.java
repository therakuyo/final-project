package textbasedgame.finalproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Rarity {

    COMMON(1),
    MAGIC(2),
    EPIC(3),
    LEGENDARY(4),
    PRIMAL(5);


    private final int priority;

    public static Rarity safeValueOf(String value){

        try {

            return Rarity.valueOf(value);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
