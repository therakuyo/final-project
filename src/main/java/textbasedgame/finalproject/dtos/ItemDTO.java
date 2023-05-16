package textbasedgame.finalproject.dtos;

import lombok.*;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.ShopEntity;
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

    private Integer price = 0;


    public static ItemDTO from(ItemEntity itemEntity) {

        return ItemDTO.builder()
            .type(itemEntity.getType())
            .status(itemEntity.getStatus())
            .rarity(itemEntity.getRarity())
            .price(itemEntity.getPrice())
            .build();

    }

}
