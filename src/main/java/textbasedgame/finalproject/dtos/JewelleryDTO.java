package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.JewelleryEntity;
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

    @NotEmpty
    @NoDigits
    private String type;

    private Rarity rarity;

    @NotEmpty
    @NoDigits
    private String name;

    private Integer attackDamage;

    private Integer magicDamage;

    private Integer healthPoints;

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
