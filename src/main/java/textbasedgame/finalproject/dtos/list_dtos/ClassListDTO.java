package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.ClassDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ClassListDTO {

    List<ClassDTO> classes;

}
