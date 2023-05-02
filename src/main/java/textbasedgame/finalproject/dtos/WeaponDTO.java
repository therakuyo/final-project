package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.WeaponEntity;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Requirements;
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
public class WeaponDTO {

    @NotEmpty
    @NoDigits
    private String type;

    private Rarity rarity;

    @NotEmpty
    @NoDigits
    private String name;

    @Min(10)
    private Integer attackDamage;

    private Requirements requirements;


    public static WeaponDTO from(WeaponEntity weaponEntity){

        return WeaponDTO.builder()
            .type(weaponEntity.getType())
            .rarity(weaponEntity.getRarity())
            .name(weaponEntity.getName())
            .attackDamage(weaponEntity.getAttackDamage())
            .requirements(weaponEntity.getRequirements())
            .build();

    }

    public static List<WeaponDTO> from(List<WeaponEntity> weaponEntities){

        List<WeaponDTO> result = new ArrayList<>();

        for (WeaponEntity weaponEntity : weaponEntities){
            result.add(WeaponDTO.from(weaponEntity));
        }

        return result;

    }

}
