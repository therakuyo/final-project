package textbasedgame.finalproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import textbasedgame.finalproject.exceptions.*;
import textbasedgame.finalproject.responses.ErrorResponse;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@ControllerAdvice
public class GameGlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(NonexistentResourceException.class)
    public ResponseEntity<ErrorResponse> handleNonexistentResourceException(NonexistentResourceException e) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(NonexistentCharacterException.class)
    public ResponseEntity<ErrorResponse> handleNonexistentCharacterException(NonexistentCharacterException e) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    @ExceptionHandler(MaxOfItemTypeEquippedExceeded.class)
    public ResponseEntity<ErrorResponse> handleMaxOfItemTypeEquippedExceededException(MaxOfItemTypeEquippedExceeded e){

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.TOO_MANY_REQUESTS);

    }


    @ExceptionHandler(ItemAlreadyEquippedException.class)
    public ResponseEntity<ErrorResponse> handleItemAlreadyEquippedException(ItemAlreadyEquippedException e){

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ItemIsNotEquippedException.class)
    public ResponseEntity<ErrorResponse> handleItemIsNotEquippedException(ItemIsNotEquippedException e){

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(ZonesDontMatchException.class)
    public ResponseEntity<ErrorResponse> handleZonesDontMatchException(ZonesDontMatchException e){

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(NotEnoughExperiencePointsToLevelUp.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughExperiencePointsToLevelUp(NotEnoughExperiencePointsToLevelUp e){

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
