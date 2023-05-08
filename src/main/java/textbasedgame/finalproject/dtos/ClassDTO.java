package textbasedgame.finalproject.dtos;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import textbasedgame.finalproject.entities.ClassEntity;
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
public class ClassDTO {

    @NotEmpty
    @NoDigits
    private String name;


    public static ClassDTO from(ClassEntity classEntity){

        return ClassDTO.builder()
            .name(classEntity.getClassName())
            .build();

    }


    public static List<ClassDTO> from(List<ClassEntity> classEntities){

        List<ClassDTO> result = new ArrayList<>();

        for (ClassEntity classEntity : classEntities){
            result.add(ClassDTO.from(classEntity));
        }

        return result;

    }

}
