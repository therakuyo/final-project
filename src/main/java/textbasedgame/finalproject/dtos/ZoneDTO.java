package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.enums.Difficulty;
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
public class ZoneDTO {

    @NotEmpty
    @NoDigits
    private String zoneName;

    private Difficulty difficulty;

    public static ZoneDTO from(ZoneEntity zoneEntity){

        return ZoneDTO.builder()
            .zoneName(zoneEntity.getZoneName())
            .difficulty(zoneEntity.getDifficulty())
            .build();

    }


    public static List<ZoneDTO> from(List<ZoneEntity> zoneEntities){

        List<ZoneDTO> result = new ArrayList<>();

        for (ZoneEntity zoneEntity : zoneEntities){
            result.add(ZoneDTO.from(zoneEntity));
        }

        return result;

    }

}
