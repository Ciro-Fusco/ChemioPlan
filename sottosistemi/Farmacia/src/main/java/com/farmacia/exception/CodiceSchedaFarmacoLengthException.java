package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per la lunghezza del codice del farmaco errata.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class CodiceSchedaFarmacoLengthException extends RuntimeException {

  public CodiceSchedaFarmacoLengthException() {}

  public CodiceSchedaFarmacoLengthException(String message) {
    super(message);
  }
}
