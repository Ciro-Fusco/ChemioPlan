package com.farmacia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 */
@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(SchedaFarmacoNotFoundException.class)
  public ResponseEntity<String> handleSchedaFarmacoNotFoundException(SchedaFarmacoNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(SchedaFarmacoAlreadyExistException.class)
  public ResponseEntity<String> handleSchedaFarmacoAlreadyExistException(SchedaFarmacoAlreadyExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrdineAlreadyExistException.class)
  public ResponseEntity<String> handleOrdineAlreadyExistException(OrdineAlreadyExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrdineFormatoQuantitaNonCorrettaException.class)
  public ResponseEntity<String> handleOrdineFormatoQuantitaNonCorrettaException(OrdineFormatoQuantitaNonCorrettaException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrdineNotFoundException.class)
  public ResponseEntity<String> handleOrdineNotFoundException(OrdineNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }
}
