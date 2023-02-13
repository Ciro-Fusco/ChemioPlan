package com.farmacia.exception;

/**
 * <p>Questa classe crea eccezioni per il formato della data.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class OldDateException extends RuntimeException {
  public OldDateException() {
  }

  public OldDateException(String message) {
    super(message);
  }
}
