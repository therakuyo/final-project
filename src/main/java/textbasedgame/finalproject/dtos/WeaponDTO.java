package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.enums.Requirements;
import textbasedgame.finalproject.validators.NoDigits;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

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

}
