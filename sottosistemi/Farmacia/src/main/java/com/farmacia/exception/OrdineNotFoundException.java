package com.farmacia.exception;

/**
 * <p>Questa classe crea eccezioni per un ordine non trovato.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class OrdineNotFoundException extends RuntimeException {
  public OrdineNotFoundException() {
  }

  public OrdineNotFoundException(String message) {
    super(message);
  }
}
