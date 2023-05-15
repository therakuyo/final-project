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
import textbasedgame.finalproject.dtos.CharacterDTO;
import textbasedgame.finalproject.dtos.EnemyDTO;
import textbasedgame.finalproject.dtos.list_dtos.EnemyListDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.entities.EnemyEntity;
import textbasedgame.finalproject.exceptions.ItemAlreadyEquippedException;
import textbasedgame.finalproject.exceptions.ItemIsNotEquippedException;
import textbasedgame.finalproject.exceptions.MaxOfItemTypeEquippedExceeded;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.AssignToZoneService;
import textbasedgame.finalproject.services.EnemyService;
import textbasedgame.finalproject.services.EquipItemService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/enemies")
@Validated
@Slf4j
public class EnemyResource {

    @Autowired
    private EnemyService enemyService;

    @Autowired
    private AssignToZoneService assignToZoneService;

    @Autowired
    private EquipItemService equipItemService;


    @Operation(summary = "Get all enemies")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = EnemyListDTO.class))
    )
    @GetMapping
    public ResponseEntity<EnemyListDTO> getAll() {

        Iterable<EnemyEntity> enemies = this.enemyService.getAll();

        List<EnemyDTO> enemyDTOS = new ArrayList<>();

        for (EnemyEntity enemyEntity : enemies) {
            enemyDTOS.add(EnemyDTO.from(enemyEntity));
        }

        EnemyListDTO enemyListDTO = new EnemyListDTO(enemyDTOS);

        return new ResponseEntity<>(enemyListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Get enemy by id")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = EnemyDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @GetMapping("/{id}")
    public ResponseEntity<EnemyDTO> getById(@Min(1) @PathVariable("id") int id) throws NonexistentResourceException {

        EnemyEntity enemyEntity = this.enemyService.findById(id);

        EnemyDTO enemyDTO = EnemyDTO.from(enemyEntity);

        return new ResponseEntity<>(enemyDTO, HttpStatus.OK);

    }


    @Operation(summary = "Delete enemy by id")
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

        this.enemyService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Create enemy")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = EnemyDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PostMapping()
    public ResponseEntity<EnemyDTO> create(@Valid @RequestBody EnemyDTO enemyDTO) {

        EnemyEntity enemyEntity = this.enemyService.add(enemyDTO);

        return new ResponseEntity<>(EnemyDTO.from(enemyEntity), HttpStatus.CREATED);

    }


    @Operation(summary = "Update enemy")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = EnemyDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<EnemyDTO> update(@Min(1) @PathVariable("id") int id, @Valid @RequestBody EnemyDTO enemyDTO)
        throws NonexistentResourceException {

        EnemyEntity enemyEntity = this.enemyService.update(id, enemyDTO);

        EnemyDTO responseDTO = EnemyDTO.from(enemyEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Assign enemy to zone")
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
    @PatchMapping("/{enemyId}/assign/{zoneId}")
    public ResponseEntity<Void> assignToZone(@Min(1) @PathVariable int enemyId,
                                             @Min(1) @PathVariable int zoneId) throws NonexistentResourceException {

        this.assignToZoneService.assignEnemy(enemyId, zoneId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Equip item on enemy")
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
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @ApiResponse(
        responseCode = "429",
        description = "TOO MANY REQUESTS",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{enemyId}/equip/{itemId}")
    public ResponseEntity<Void> equipItem(@Min(1) @PathVariable("enemyId") int enemyId,
                                          @Min(1) @PathVariable("itemId") int itemId)
        throws NonexistentResourceException, MaxOfItemTypeEquippedExceeded, ItemAlreadyEquippedException {

        this.equipItemService.equipItemOnEnemy(enemyId, itemId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Unequip item from enemy")
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
    @ApiResponse(
        responseCode = "400",
        description = "BAD REQUEST",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{enemyId}/unequip/{itemId}")
    public ResponseEntity<Void> unequipItem(@Min(1) @PathVariable("enemyId") int enemyId,
                                            @Min(1) @PathVariable("itemId") int itemId)
        throws NonexistentResourceException, ItemIsNotEquippedException {

        this.equipItemService.unequipItemFromEnemy(enemyId, itemId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


}
