package textbasedgame.finalproject.dtos;

import lombok.*;
import textbasedgame.finalproject.enums.ItemType;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Status;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

    private ItemType type;

    private Rarity rarity;

    private Status status = Status.UNEQUIPPED;

}
