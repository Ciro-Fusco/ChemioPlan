package com.farmacia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>Questa classe gestisce le eccezioni del sistema.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
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

  @ExceptionHandler(FormatoQuantitaNonCorrettaException.class)
  public ResponseEntity<String> handleFormatoQuantitaNonCorrettaException(FormatoQuantitaNonCorrettaException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OrdineNotFoundException.class)
  public ResponseEntity<String> handleOrdineNotFoundException(OrdineNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(LottoAlreadyExistException.class)
  public ResponseEntity<String> handleLottoArleadyExistException(LottoAlreadyExistException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LottoNotFoundException.class)
  public ResponseEntity<String> handleLottoNotFoundException(LottoNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(CodiceSchedaFarmacoLengthException.class)
  public ResponseEntity<String> handleCodiceSchedaFarmacoLengthException(CodiceSchedaFarmacoLengthException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NomeSchedaFarmacoLenghtException.class)
  public ResponseEntity<String> handleNomeSchedaFarmacoLenghtException(NomeSchedaFarmacoLenghtException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(OldDateException.class)
  public ResponseEntity<String> handleOldDateException(OldDateException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
