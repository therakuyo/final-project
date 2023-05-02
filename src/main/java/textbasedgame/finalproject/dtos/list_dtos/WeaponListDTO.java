package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.WeaponDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class WeaponListDTO {

    private List<WeaponDTO> weapons;

}
