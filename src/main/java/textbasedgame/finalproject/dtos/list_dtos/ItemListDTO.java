package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.ItemDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ItemListDTO {

    private List<ItemDTO> items;

}
