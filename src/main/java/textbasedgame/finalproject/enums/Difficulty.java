package textbasedgame.finalproject.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Difficulty {

    EASY(1,4),
    MEDIUM(5,9),
    HARD(10,14),
    EXTREME(15,20);


    private final int start;

    private final int end;


    public static Difficulty safeValueOf(String value){

        try {

            return Difficulty.valueOf(value);

        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return null;
        }

    }


}
