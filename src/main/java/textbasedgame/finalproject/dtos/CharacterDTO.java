package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.CharacterEntity;
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
public class CharacterDTO {

    @NotEmpty
    @NoDigits
    private String name;

    @Min(1)
    private Integer level;

    @NotEmpty
    @NoDigits
    private String className;


    public static CharacterDTO from(CharacterEntity characterEntity) {

        return CharacterDTO.builder()
            .name(characterEntity.getName())
            .level(characterEntity.getLevel())
            .className(characterEntity.getCharacterClass().getClassName())
            .build();

    }


    public static List<CharacterDTO> from(List<CharacterEntity> characterEntities){

        List<CharacterDTO> result = new ArrayList<>();

        for (CharacterEntity characterEntity : characterEntities){
            result.add(CharacterDTO.from(characterEntity));
        }

        return result;

    }

}