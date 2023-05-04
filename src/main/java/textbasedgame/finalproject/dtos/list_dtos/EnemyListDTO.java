package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.EnemyDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EnemyListDTO {

    private List<EnemyDTO> enemies;

}
