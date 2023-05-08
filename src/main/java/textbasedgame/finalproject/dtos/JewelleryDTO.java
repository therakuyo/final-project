package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import textbasedgame.finalproject.entities.JewelleryEntity;
import textbasedgame.finalproject.enums.ItemType;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.validators.NoDigits;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class JewelleryDTO {

    private ItemType type;

    private Rarity rarity;

    @NotEmpty
    @NoDigits
    private String name;

    @Nullable
    private Integer attackDamage;

    @Nullable
    private Integer magicDamage;

    @Nullable
    private Integer healthPoints;

    @Nullable
    private Integer resistance;


    public static JewelleryDTO from(JewelleryEntity jewelleryEntity){

        return JewelleryDTO.builder()
            .type(jewelleryEntity.getType())
            .rarity(jewelleryEntity.getRarity())
            .name(jewelleryEntity.getName())
            .attackDamage(jewelleryEntity.getAttackDamage())
            .magicDamage(jewelleryEntity.getMagicDamage())
            .healthPoints(jewelleryEntity.getHealthPoints())
            .resistance(jewelleryEntity.getResistance())
            .build();

    }

    public static List<JewelleryDTO> from(List<JewelleryEntity> jewelleryEntities){

        List<JewelleryDTO> result = new ArrayList<>();

        for (JewelleryEntity jewelleryEntity : jewelleryEntities){
            result.add(JewelleryDTO.from(jewelleryEntity));
        }

        return result;

    }

}
