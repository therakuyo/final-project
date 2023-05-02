package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.ArmourEntity;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.validators.NoDigits;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ArmourDTO {

    @NotEmpty
    @NoDigits
    private String type;

    private Rarity rarity;

    @NotEmpty
    @NoDigits
    private String name;

    @Min(50)
    private Integer healthPoints;

    @Min(10)
    private Integer resistance;


    public static ArmourDTO from(ArmourEntity armourEntity){

        return ArmourDTO.builder()
            .name(armourEntity.getName())
            .type(armourEntity.getType())
            .rarity(armourEntity.getRarity())
            .healthPoints(armourEntity.getHealthPoints())
            .resistance(armourEntity.getResistance())
            .build();

    }

    public static List<ArmourDTO> from(List<ArmourEntity> armourEntities){

        List<ArmourDTO> result = new ArrayList<>();

        for (ArmourEntity armourEntity : armourEntities){
            result.add(ArmourDTO.from(armourEntity));
        }

        return result;

    }

}
