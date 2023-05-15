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
import textbasedgame.finalproject.dtos.ClassDTO;
import textbasedgame.finalproject.dtos.EnemyDTO;
import textbasedgame.finalproject.dtos.list_dtos.CharacterListDTO;
import textbasedgame.finalproject.dtos.list_dtos.ClassListDTO;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.exceptions.NonexistentResourceException;
import textbasedgame.finalproject.services.ClassService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Slf4j
@RequestMapping("/api/classes")
public class ClassResource {

    @Autowired
    private ClassService classService;


    @Operation(summary = "Create class")
    @ApiResponse(
        responseCode = "201",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ClassDTO.class))
    )
    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> create(@Valid @RequestBody ClassDTO classDTO) {

        ClassEntity classEntity = this.classService.add(classDTO);

        return new ResponseEntity<>(ClassDTO.from(classEntity), HttpStatus.CREATED);

    }


    @Operation(summary = "Get all classes")
    @ApiResponse(
        responseCode = "200",
        description = "OK",
        content = @Content(schema = @Schema(implementation = ClassListDTO.class))
    )
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PLAYER')")
    public ResponseEntity<ClassListDTO> getAll(){

        Iterable<ClassEntity> classEntities = this.classService.getAll();

        List<ClassDTO> classDTOS = new ArrayList<>();

        for (ClassEntity classEntity : classEntities){
            classDTOS.add(ClassDTO.from(classEntity));
        }

        ClassListDTO classListDTO = new ClassListDTO(classDTOS);

        return new ResponseEntity<>(classListDTO, HttpStatus.OK);

    }


    @Operation(summary = "Delete class by id")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@Min(1) @PathVariable("id") int id) throws NonexistentResourceException {

        this.classService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    @Operation(summary = "Update class")
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ClassDTO> update(@Min(1) @PathVariable("id") int id, @Valid @RequestBody ClassDTO classDTO)
        throws NonexistentResourceException {

        ClassEntity classEntity = this.classService.update(id, classDTO);

        ClassDTO responseDTO = ClassDTO.from(classEntity);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}
