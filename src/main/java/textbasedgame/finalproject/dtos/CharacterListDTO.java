package textbasedgame.finalproject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CharacterListDTO {

    private List<CharacterDTO> characters;

}
