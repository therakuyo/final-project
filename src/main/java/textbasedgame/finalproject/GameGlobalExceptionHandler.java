package textbasedgame.finalproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import textbasedgame.finalproject.exceptions.*;
import textbasedgame.finalproject.responses.ErrorResponse;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class GameGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {

        log.debug("Constraint violation {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {

        log.debug("General Exception: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NonexistentResourceException.class)
    public ResponseEntity<ErrorResponse> handleNonexistentResourceException(NonexistentResourceException e) {

        log.debug("This type of resource doesn't exist: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(NonexistentCharacterException.class)
    public ResponseEntity<ErrorResponse> handleNonexistentCharacterException(NonexistentCharacterException e) {

        log.debug("This character doesn't exist: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(MaxOfItemTypeEquippedExceeded.class)
    public ResponseEntity<ErrorResponse> handleMaxOfItemTypeEquippedExceededException(MaxOfItemTypeEquippedExceeded e){

        log.debug("Can't equip any more items of this type, try another type or unequipping: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.TOO_MANY_REQUESTS);

    }


    @ExceptionHandler(ItemAlreadyEquippedException.class)
    public ResponseEntity<ErrorResponse> handleItemAlreadyEquippedException(ItemAlreadyEquippedException e){

        log.debug("This item is already equipped on the resource: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ItemIsNotEquippedException.class)
    public ResponseEntity<ErrorResponse> handleItemIsNotEquippedException(ItemIsNotEquippedException e){

        log.debug("The item is not equipped on the resource: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ZonesDontMatchException.class)
    public ResponseEntity<ErrorResponse> handleZonesDontMatchException(ZonesDontMatchException e){

        log.debug("Character and enemy are in different zones so they can't fight: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NotEnoughExperiencePointsToLevelUp.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughExperiencePointsToLevelUp(NotEnoughExperiencePointsToLevelUp e){

        log.debug("You need more experience points in order to level up: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e){

        log.debug("A user has already been registered with this account: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(CharacterAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCharacterAlreadyExistsException(CharacterAlreadyExistsException e){

        log.debug("A character with this name has already been created: {}", e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
