package textbasedgame.finalproject.dtos;

import lombok.*;
import textbasedgame.finalproject.enums.Rarity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private String type;

    private Rarity rarity;

}
