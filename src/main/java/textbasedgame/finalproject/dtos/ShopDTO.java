package textbasedgame.finalproject.dtos;

import lombok.*;
import textbasedgame.finalproject.entities.ShopEntity;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShopDTO {

    @NotEmpty
    private String name;


    public static ShopDTO from(ShopEntity shopEntity) {

        return ShopDTO.builder()
            .name(shopEntity.getName())
            .build();

    }


    public static List<ShopDTO> from(List<ShopEntity> shopEntities){

        List<ShopDTO> result = new ArrayList<>();

        for (ShopEntity shopEntity : shopEntities){
            result.add(ShopDTO.from(shopEntity));
        }

        return result;

    }

}
