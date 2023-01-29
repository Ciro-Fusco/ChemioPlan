package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per la lunghezza del nome del formaco.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class NomeSchedaFarmacoLenghtException extends RuntimeException {

  public NomeSchedaFarmacoLenghtException() {}

  public NomeSchedaFarmacoLenghtException(String message) {
    super(message);
  }
}
