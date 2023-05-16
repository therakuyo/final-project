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
import textbasedgame.finalproject.dtos.ArmourDTO;
import textbasedgame.finalproject.dtos.list_dtos.ArmourListDTO;
import textbasedgame.finalproject.entities.ArmourEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.ArmourService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/armour")
@Validated
@Slf4j
public class ArmourResource {

    @Autowired
    private ArmourService armourService;


    @Operation(summary = "Get all armour")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ArmourListDTO.class))
    )
    @GetMapping
    public ResponseEntity<ArmourListDTO> getAll() {

        Iterable<ArmourEntity> armour = this.armourService.getAllArmour();

        List<ArmourDTO> armourDTOS = new ArrayList<>();

        for (ArmourEntity armourEntity : armour) {
            armourDTOS.add(ArmourDTO.from(armourEntity));
        }

        ArmourListDTO armourListDTO = new ArmourListDTO(armourDTOS);

        return new ResponseEntity<>(armourListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Create armour")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ArmourDTO.class))
    )
    @PostMapping
    public ResponseEntity<ArmourDTO> create(@Valid @RequestBody ArmourDTO armour) {

        ArmourEntity armourEntity = this.armourService.add(armour);

        return new ResponseEntity<>(ArmourDTO.from(armourEntity), HttpStatus.CREATED);

    }


    @Operation(summary = "Delete armour by id")
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
    public ResponseEntity<Void> delete(@Min(13) @PathVariable("id") int id) throws NonexistentResourceException {

        this.armourService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Complete update of armour")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ArmourDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<ArmourDTO> updateComplete(@Min(13) @PathVariable("id") int id,
                                                    @Valid @RequestBody ArmourDTO armourDTO)
        throws NonexistentResourceException {

        ArmourEntity armourEntity = this.armourService.updateComplete(id, armourDTO);

        ArmourDTO responseDTO = ArmourDTO.from(armourEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Partial update of armour")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ArmourDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{id}")
    public ResponseEntity<ArmourDTO> updatePartial(@Min(13) @PathVariable("id") int id, @RequestBody ArmourDTO armourDTO)
        throws NonexistentResourceException {

        ArmourEntity armourEntity = this.armourService.updatePartial(id, armourDTO);

        ArmourDTO responseDTO = ArmourDTO.from(armourEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
