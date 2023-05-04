package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.EnemyEntity;
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
public class EnemyDTO {

    @NotEmpty
    @NoDigits
    private String name;


    public static EnemyDTO from(EnemyEntity enemyEntity){

        return EnemyDTO.builder()
            .name(enemyEntity.getName())
            .build();

    }


    public static List<EnemyDTO> from(List<EnemyEntity> enemyEntities){

        List<EnemyDTO> result = new ArrayList<>();

        for (EnemyEntity enemyEntity : enemyEntities){
            result.add(EnemyDTO.from(enemyEntity));
        }

        return result;

    }

}
