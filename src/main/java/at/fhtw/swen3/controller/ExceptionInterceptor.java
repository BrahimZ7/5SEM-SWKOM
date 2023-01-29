package at.fhtw.swen3.controller;


import at.fhtw.swen3.services.exception.BLNotFoundException;
import at.fhtw.swen3.services.dto.Error;
import at.fhtw.swen3.services.exception.BLValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler({BLNotFoundException.class})
    public ResponseEntity<Error> handleNotFoundException(BLNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error().errorMessage(exception.getMessage()));
    }

    @ExceptionHandler({BLValidationException.class})
    public ResponseEntity<Error> handleValidationException(BLValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Error().errorMessage(exception.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error().errorMessage(exception.getMessage()));
    }

}
