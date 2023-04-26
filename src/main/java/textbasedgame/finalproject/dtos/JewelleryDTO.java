package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.enums.Rarity;
import textbasedgame.finalproject.validators.NoDigits;

import javax.validation.constraints.NotEmpty;

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

}
