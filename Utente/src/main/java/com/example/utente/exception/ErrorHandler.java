package com.example.utente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(UtenteAlreadyExistException.class)
  public ResponseEntity<String> handleUtenteAlreadyExistException(UtenteAlreadyExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UtenteNotFoundException.class)
  public ResponseEntity<String> handleUtenteNotFoundException(UtenteNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CredenzialiNonValideException.class)
  public ResponseEntity<String> handleCredenzialiNonValideException(CredenzialiNonValideException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
