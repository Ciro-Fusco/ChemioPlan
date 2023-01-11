package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per una scheda farmaco già esistente.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class SchedaFarmacoAlreadyExistException extends RuntimeException {
  public SchedaFarmacoAlreadyExistException() {}

  public SchedaFarmacoAlreadyExistException(String message) {
    super(message);
  }
}
