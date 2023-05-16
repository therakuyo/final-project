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
import textbasedgame.finalproject.dtos.ZoneDTO;
import textbasedgame.finalproject.dtos.list_dtos.ZoneListDTO;
import textbasedgame.finalproject.entities.ZoneEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.ZoneService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/zones")
public class ZoneResource {

    @Autowired
    private ZoneService zoneService;


    @Operation(summary = "Get all zones")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ZoneListDTO.class))
    )
    @GetMapping
    public ResponseEntity<ZoneListDTO> getAll() {

        Iterable<ZoneEntity> zones = this.zoneService.getAll();

        List<ZoneDTO> zoneDTOS = new ArrayList<>();

        for (ZoneEntity zoneEntity : zones) {
            zoneDTOS.add(ZoneDTO.from(zoneEntity));
        }

        ZoneListDTO zoneListDTO = new ZoneListDTO(zoneDTOS);

        return new ResponseEntity<>(zoneListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Get zone by id")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ZoneDTO.class)))
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class)))
    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getById(@Min(5) @PathVariable("id") int id) throws NonexistentResourceException {

        ZoneEntity zoneEntity = this.zoneService.findById(id);

        ZoneDTO zoneDTO = ZoneDTO.from(zoneEntity);

        return new ResponseEntity<>(zoneDTO, HttpStatus.OK);

    }


    @Operation(summary = "Create zone")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ZoneDTO.class))
    )
    @PostMapping()
    public ResponseEntity<ZoneDTO> create(@Valid @RequestBody ZoneDTO zoneDTO) {

        ZoneEntity zoneEntity = this.zoneService.add(zoneDTO);

        return new ResponseEntity<>(ZoneDTO.from(zoneEntity), HttpStatus.CREATED);

    }


    @Operation(summary = "Delete zone by id")
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
    public ResponseEntity<Void> delete(@Min(5) @PathVariable("id") int id) throws NonexistentResourceException {

        this.zoneService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Complete update of zone")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ZoneDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> updateComplete(@Min(5) @PathVariable("id") int id,
                                                  @Valid @RequestBody ZoneDTO zoneDTO)
        throws NonexistentResourceException {

        ZoneEntity zoneEntity = this.zoneService.updateComplete(id, zoneDTO);

        ZoneDTO responseDTO = ZoneDTO.from(zoneEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Partial update of zone")
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
    public ResponseEntity<ZoneDTO> updatePartial(@Min(5) @PathVariable("id") int id, @RequestBody ZoneDTO zoneDTO)
        throws NonexistentResourceException {

        ZoneEntity zoneEntity = this.zoneService.updatePartial(id, zoneDTO);

        ZoneDTO responseDTO = ZoneDTO.from(zoneEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
