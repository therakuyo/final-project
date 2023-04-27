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
import textbasedgame.finalproject.dtos.CharacterListDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.exceptions.NonexistentCharacterException;
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


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException e) {

        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }


    public ResponseEntity<CharacterListDTO> getAll() {

        Iterable<CharacterEntity> characters = this.characterService.getAll();

        List<CharacterDTO> characterDTOS = new ArrayList<>();

        for (CharacterEntity characterEntity : characters) {
            characterDTOS.add(CharacterDTO.from(characterEntity));
        }

        CharacterListDTO characterListDTO = new CharacterListDTO(characterDTOS);

        return new ResponseEntity<>(characterListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Get character by name (id)")
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


    @DeleteMapping("/{name}")
    public ResponseEntity<Void> delete(@NotEmpty @PathVariable("name") String name) {

        this.characterService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @PostMapping
    public ResponseEntity<CharacterDTO> create(@Min(1) @PathVariable int id,
                                               @Valid @RequestBody CharacterDTO character) {

        CharacterEntity characterEntity = this.characterService.add(character, id);
        return new ResponseEntity<>(CharacterDTO.from(characterEntity), HttpStatus.CREATED);

    }


    @PatchMapping("/{name}")
    public ResponseEntity<CharacterDTO> changeName(@PathVariable("name") String name,
                                                   @RequestBody CharacterDTO characterDTO) {
        try {
            CharacterEntity characterEntity = this.characterService.changeName(name, characterDTO);

            CharacterDTO responseDTO = CharacterDTO.from(characterEntity);

            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (NonexistentCharacterException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
