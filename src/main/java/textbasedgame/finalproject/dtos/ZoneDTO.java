package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.enums.Difficulty;
import textbasedgame.finalproject.validators.NoDigits;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ZoneDTO {

    @NotEmpty
    @NoDigits
    private String zoneName;

    private Difficulty difficulty;

}
