package textbasedgame.finalproject.dtos.list_dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import textbasedgame.finalproject.dtos.ShopDTO;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ShopListDTO {

    private List<ShopDTO> shops;

}
