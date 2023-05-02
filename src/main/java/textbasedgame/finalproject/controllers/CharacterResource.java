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
import textbasedgame.finalproject.dtos.list_dtos.CharacterListDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.AssignToZoneService;
import textbasedgame.finalproject.services.CharacterService;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/characters")
@Validated
@Slf4j
public class CharacterResource {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private AssignToZoneService assignToZoneService;


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Get all characters")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterListDTO.class))
    )
    public ResponseEntity<CharacterListDTO> getAll() {

        Iterable<CharacterEntity> characters = this.characterService.getAll();

        List<CharacterDTO> characterDTOS = new ArrayList<>();

        for (CharacterEntity characterEntity : characters) {
            characterDTOS.add(CharacterDTO.from(characterEntity));
        }

        CharacterListDTO characterListDTO = new CharacterListDTO(characterDTOS);

        return new ResponseEntity<>(characterListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Get character by name")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @GetMapping("/{name}")
    public ResponseEntity<CharacterDTO> getByName(@NotEmpty @PathVariable("name") String name) {

        Optional<CharacterEntity> optionalCharacter = this.characterService.findByName(name);
        if (!optionalCharacter.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CharacterEntity characterEntity = optionalCharacter.get();
        CharacterDTO characterDTO = CharacterDTO.from(characterEntity);

        return new ResponseEntity<>(characterDTO, HttpStatus.OK);

    }

    @Operation(summary = "Delete character by id")
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
    public ResponseEntity<Void> delete(@NotEmpty @PathVariable("id") int id) {

        try {

            this.characterService.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (NonexistentResourceException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Operation(summary = "Create character")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PostMapping
    public ResponseEntity<CharacterDTO> create(@Min(1) @PathVariable int id,
                                               @Valid @RequestBody CharacterDTO character) {

        try {

            CharacterEntity characterEntity = this.characterService.add(character, id);

            return new ResponseEntity<>(CharacterDTO.from(characterEntity), HttpStatus.CREATED);

        } catch (NonexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


    @Operation(summary = "Change character's name")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDTO> changeName(@PathVariable("id") int id,
                                                   @RequestBody CharacterDTO characterDTO) {
        try {
            CharacterEntity characterEntity = this.characterService.changeName(id, characterDTO);

            CharacterDTO responseDTO = CharacterDTO.from(characterEntity);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (NonexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Operation(summary = "Assign character to zone")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterDTO.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "NOT FOUND",
        content = @Content(schema = @Schema(implementation = Void.class))
    )
    @PatchMapping("/{characterId}/assign/{zoneId}")
    public ResponseEntity<Void> assignToZone(@PathVariable("characterId") int characterId,
                                             @PathVariable("zoneId") int zoneId) {

        try {

            this.assignToZoneService.assignCharacter(characterId, zoneId);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (NonexistentResourceException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
