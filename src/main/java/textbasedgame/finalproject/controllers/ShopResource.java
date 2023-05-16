package textbasedgame.finalproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import textbasedgame.finalproject.dtos.ItemDTO;
import textbasedgame.finalproject.dtos.ShopDTO;
import textbasedgame.finalproject.dtos.list_dtos.ItemListDTO;
import textbasedgame.finalproject.dtos.list_dtos.ShopListDTO;
import textbasedgame.finalproject.entities.ItemEntity;
import textbasedgame.finalproject.entities.ShopEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.ShopService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/shops")
@Slf4j
@Validated
public class ShopResource {

    @Autowired
    private ShopService shopService;


    @Operation(summary = "Get all shops")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ShopListDTO.class))
    )
    @GetMapping
    public ResponseEntity<ShopListDTO> getAll() {

        Iterable<ShopEntity> shops = this.shopService.getAll();

        List<ShopDTO> shopDTOS = new ArrayList<>();

        for (ShopEntity shopEntity : shops) {
            shopDTOS.add(ShopDTO.from(shopEntity));
        }

        ShopListDTO shopListDTO = new ShopListDTO(shopDTOS);

        return new ResponseEntity<>(shopListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Create shop")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ShopDTO.class))
    )
    @PostMapping
    public ResponseEntity<ShopDTO> create(@Valid @RequestBody ShopDTO shopDTO) {

        ShopEntity shop = this.shopService.add(shopDTO);

        return new ResponseEntity<>(ShopDTO.from(shop), HttpStatus.CREATED);

    }


    @Operation(summary = "Update shop")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ShopDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<ShopDTO> update(@Min(1) @PathVariable("id") int id, @Valid @RequestBody ShopDTO shopDTO)
        throws NonexistentResourceException {

        ShopEntity shopEntity = this.shopService.update(id, shopDTO);

        ShopDTO responseDTO = ShopDTO.from(shopEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Delete shop by id")
    @ApiResponse(
        responseCode = "204",
        description = "NO_CONTENT",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Min(1) @PathVariable("id") int id) throws NonexistentResourceException {

        this.shopService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Add item to shop with price")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{itemId}/to/{shopId}/{price}")
    public ResponseEntity<Void> addItemToShop(@Min(1) @PathVariable("itemId") int itemId,
                                              @Min(1) @PathVariable("shopId") int shopId,
                                              @Min(1) @PathVariable("price") int price)
        throws NonexistentResourceException {

        this.shopService.addItemToShop(itemId, shopId, price);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
