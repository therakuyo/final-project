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
import textbasedgame.finalproject.dtos.WeaponDTO;
import textbasedgame.finalproject.dtos.list_dtos.WeaponListDTO;
import textbasedgame.finalproject.entities.WeaponEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.WeaponService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/weapons")
@Validated
@Slf4j
public class WeaponResource {

    @Autowired
    private WeaponService weaponService;


    @Operation(summary = "Get all weapons")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = WeaponListDTO.class))
    )
    @GetMapping
    public ResponseEntity<WeaponListDTO> getAll() {

        Iterable<WeaponEntity> weapons = this.weaponService.getAllWeapons();

        List<WeaponDTO> weaponDTOS = new ArrayList<>();

        for (WeaponEntity weaponEntity : weapons) {
            weaponDTOS.add(WeaponDTO.from(weaponEntity));
        }

        WeaponListDTO weaponListDTO = new WeaponListDTO(weaponDTOS);

        return new ResponseEntity<>(weaponListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Create weapon")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = WeaponDTO.class))
    )
    @PostMapping
    public ResponseEntity<WeaponDTO> create(@Valid @RequestBody WeaponDTO weaponDTO) {

        WeaponEntity weapon = this.weaponService.add(weaponDTO);

        return new ResponseEntity<>(WeaponDTO.from(weapon), HttpStatus.CREATED);

    }


    @Operation(summary = "Delete weapon by id")
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

        this.weaponService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Complete update of weapon")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = WeaponDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<WeaponDTO> updateComplete(@Min(1) @PathVariable("id") int id,
                                                    @Valid @RequestBody WeaponDTO weaponDTO)
        throws NonexistentResourceException {

        WeaponEntity weapon = this.weaponService.updateComplete(id, weaponDTO);

        WeaponDTO responseDTO = WeaponDTO.from(weapon);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Partial update of weapon")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = WeaponDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{id}")
    public ResponseEntity<WeaponDTO> updatePartial(@Min(1) @PathVariable("id") int id, @RequestBody WeaponDTO weaponDTO)
        throws NonexistentResourceException {

        WeaponEntity weapon = this.weaponService.updatePartial(id, weaponDTO);

        WeaponDTO responseDTO = WeaponDTO.from(weapon);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
