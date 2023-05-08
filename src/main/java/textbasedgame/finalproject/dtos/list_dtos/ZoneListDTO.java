package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.ZoneDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ZoneListDTO {

    List<ZoneDTO> zones;

}
