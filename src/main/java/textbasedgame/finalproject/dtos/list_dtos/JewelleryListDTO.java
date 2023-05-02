package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.JewelleryDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JewelleryListDTO {

    private List<JewelleryDTO> jewelleryS;

}
