package com.chemioplan.SchedaPaziente.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p> Questa classe gestisce le eccezzioni del sistema.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */
@ControllerAdvice
public class ErrorHandler {

  @ExceptionHandler(SchedaPazienteNotFoundException.class)
  public ResponseEntity<String>
      handleSchedaPazienteNotFoundException(SchedaPazienteNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(SchedaPazienteAlredyExistException.class)
  public ResponseEntity<String>
      handleSchedaPazienteAlredyExistException(SchedaPazienteAlredyExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}

