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
import textbasedgame.finalproject.dtos.ClassDTO;
import textbasedgame.finalproject.entities.ClassEntity;
import textbasedgame.finalproject.services.ClassService;

import javax.validation.Valid;

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
    public ResponseEntity<ClassDTO> create(@Valid @RequestBody ClassDTO classDTO) {

        ClassEntity classEntity = this.classService.add(classDTO);

        return new ResponseEntity<>(ClassDTO.from(classEntity), HttpStatus.CREATED);

    }

}
