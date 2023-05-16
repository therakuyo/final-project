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
import textbasedgame.finalproject.dtos.JewelleryDTO;
import textbasedgame.finalproject.dtos.list_dtos.JewelleryListDTO;
import textbasedgame.finalproject.entities.JewelleryEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.JewelleryService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/jewellery")
@Validated
@Slf4j
public class JewelleryResource {

    @Autowired
    private JewelleryService jewelleryService;


    @Operation(summary = "Get all jewellery")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = JewelleryListDTO.class))
    )
    @GetMapping
    public ResponseEntity<JewelleryListDTO> getAll() {

        Iterable<JewelleryEntity> jewellery = this.jewelleryService.getAllJewellery();

        List<JewelleryDTO> jewelleryDTOS = new ArrayList<>();

        for (JewelleryEntity jewelleryEntity : jewellery) {
            jewelleryDTOS.add(JewelleryDTO.from(jewelleryEntity));
        }

        JewelleryListDTO jewelleryListDTO = new JewelleryListDTO(jewelleryDTOS);

        return new ResponseEntity<>(jewelleryListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Create jewellery")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = JewelleryDTO.class))
    )
    @PostMapping
    public ResponseEntity<JewelleryDTO> create(@Valid @RequestBody JewelleryDTO jewellery) {

        JewelleryEntity jewelleryEntity = this.jewelleryService.add(jewellery);

        return new ResponseEntity<>(JewelleryDTO.from(jewelleryEntity), HttpStatus.CREATED);

    }


    @Operation(summary = "Delete jewellery by id")
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
    public ResponseEntity<Void> delete(@Min(10) @PathVariable("id") int id) throws NonexistentResourceException {

        this.jewelleryService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Complete update of jewellery")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = JewelleryDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<JewelleryDTO> updateComplete(@Min(10) @PathVariable("id") int id,
                                                       @Valid @RequestBody JewelleryDTO jewelleryDTO)
        throws NonexistentResourceException {

        JewelleryEntity jewelleryEntity = this.jewelleryService.updateComplete(id, jewelleryDTO);

        JewelleryDTO responseDTO = JewelleryDTO.from(jewelleryEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Partial update of jewellery")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = JewelleryDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{id}")
    public ResponseEntity<JewelleryDTO> updatePartial(@Min(10) @PathVariable("id") int id,
                                                      @RequestBody JewelleryDTO jewelleryDTO)
        throws NonexistentResourceException {

        JewelleryEntity jewelleryEntity = this.jewelleryService.updatePartial(id, jewelleryDTO);

        JewelleryDTO responseDTO = JewelleryDTO.from(jewelleryEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
