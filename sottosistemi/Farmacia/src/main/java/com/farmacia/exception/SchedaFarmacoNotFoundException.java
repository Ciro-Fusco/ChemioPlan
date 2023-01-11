package com.farmacia.exception;

/**
 * <p>Questa classe crea eccezioni per una scheda farmaco non trovata.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class SchedaFarmacoNotFoundException extends RuntimeException {
  public SchedaFarmacoNotFoundException() {}

  public SchedaFarmacoNotFoundException(String message) {
    super(message);
  }
}