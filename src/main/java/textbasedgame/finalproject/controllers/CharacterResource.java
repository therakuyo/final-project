package textbasedgame.finalproject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import textbasedgame.finalproject.dtos.CharacterDTO;
import textbasedgame.finalproject.dtos.list_dtos.CharacterListDTO;
import textbasedgame.finalproject.entities.CharacterEntity;
import textbasedgame.finalproject.exceptions.*;
import textbasedgame.finalproject.services.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/characters")
@Validated
@Slf4j
public class CharacterResource {

    @Autowired
    private CharacterService characterService;

    @Autowired
    private AssignToZoneService assignToZoneService;

    @Autowired
    private EquipItemService equipItemService;

    @Autowired
    private FightService fightService;

    @Autowired
    private LevelUpService levelUpService;

    @Autowired
    private ShopService shopService;


    @Operation(summary = "Get all characters")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = CharacterListDTO.class))
    )
    @GetMapping
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
    public ResponseEntity<CharacterDTO> getByName(@Valid @PathVariable("name") String name)
        throws NonexistentCharacterException {

        CharacterEntity characterEntity = this.characterService.findByName(name);

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
    public ResponseEntity<Void> delete(@Min(1) @PathVariable("id") int id) throws NonexistentResourceException {

        this.characterService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

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
    @PostMapping("/{id}")
    public ResponseEntity<CharacterDTO> create(@Min(1) @PathVariable int id,
                                               @Valid @RequestBody CharacterDTO character)
        throws NonexistentResourceException, CharacterAlreadyExistsException {

        CharacterEntity characterEntity = this.characterService.add(character, id);

        return new ResponseEntity<>(CharacterDTO.from(characterEntity), HttpStatus.CREATED);

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
    public ResponseEntity<CharacterDTO> changeName(@Min(1) @PathVariable("id") int id,
                                                   @Valid @RequestBody CharacterDTO characterDTO)
        throws NonexistentResourceException {

        CharacterEntity characterEntity = this.characterService.changeName(id, characterDTO);

        CharacterDTO responseDTO = CharacterDTO.from(characterEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

    @Operation(summary = "Assign character to zone")
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
    @PatchMapping("/{characterId}/assign/{zoneId}")
    public ResponseEntity<Void> assignToZone(@Min(1) @PathVariable("characterId") int characterId,
                                             @Min(5) @PathVariable("zoneId") int zoneId)
        throws NonexistentResourceException, CharacterLevelDoesntMatchZoneReqException {

        this.assignToZoneService.assignCharacter(characterId, zoneId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Equip item on character")
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
    @PatchMapping("/{characterId}/equip/{itemId}")
    public ResponseEntity<Void> equipItem(@Min(1) @PathVariable("characterId") int characterId,
                                          @Min(4) @PathVariable("itemId") int itemId)
        throws NonexistentResourceException, MaxOfItemTypeEquippedExceeded, ItemAlreadyEquippedException {

        this.equipItemService.equipItemOnCharacter(characterId, itemId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Unequip item from character")
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
    @PatchMapping("/{characterId}/unequip/{itemId}")
    public ResponseEntity<Void> unequipItem(@Min(1) @PathVariable("characterId") int characterId,
                                            @Min(4) @PathVariable("itemId") int itemId)
        throws NonexistentResourceException, ItemIsNotEquippedException {

        this.equipItemService.unequipItemFromCharacter(characterId, itemId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Calculate power level for character")
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
    @PatchMapping("/calculate/{id}")
    public ResponseEntity<CharacterDTO> calculatePowerLevel(@Min(1) @PathVariable("id") int id)
        throws NonexistentResourceException {

        CharacterEntity characterEntity = this.characterService.findById(id);

        this.fightService.calculateCharacterPowerLevel(id);

        CharacterDTO responseDTO = CharacterDTO.from(characterEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @Operation(summary = "Unequip item from character")
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
    @PatchMapping("/{characterId}/fight/{enemyId}")
    public ResponseEntity<Void> fight(@Min(1) @PathVariable("characterId") int characterId,
                                      @Min(1) @PathVariable("enemyId") int enemyId)
        throws NonexistentResourceException, ZonesDontMatchException {

        this.fightService.fight(characterId, enemyId);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Level up character")
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
    @PatchMapping("/levelup/{id}")
    public ResponseEntity<Void> levelUp(@Min(1) @PathVariable("id") int id)
        throws NonexistentResourceException, NotEnoughExperiencePointsToLevelUp {

        this.levelUpService.levelUp(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Check level up progress")
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
    @GetMapping("/progress/{id}")
    public ResponseEntity<Void> checkProgress(@Min(1) @PathVariable("id") int id) throws NonexistentResourceException {

        this.levelUpService.checkProgress(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }


    @Operation(summary = "Buy item from shop for character")
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
    @PatchMapping("/{characterId}/buy/{itemId}/from/{shopId}")
    public ResponseEntity<Void> buyItem(@Min(1) @PathVariable("characterId") int characterId,
                                        @Min(4) @PathVariable("itemId") int itemId,
                                        @Min(1) @PathVariable("shopId") int shopId)
        throws NonexistentResourceException, NotEnoughGoldToBuyItemException {

        this.characterService.buyItem(characterId, itemId);
        this.shopService.removeItemFromShop(itemId, shopId);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
