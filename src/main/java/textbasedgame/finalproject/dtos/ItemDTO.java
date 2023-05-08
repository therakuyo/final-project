package textbasedgame.finalproject.dtos;

import lombok.*;
import textbasedgame.finalproject.enums.ItemType;
import textbasedgame.finalproject.enums.Rarity;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private ItemType type;

    private Rarity rarity;

}
