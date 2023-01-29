package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per un lotto non trovato.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class LottoNotFoundException extends RuntimeException {
  public LottoNotFoundException() {
  }

  public LottoNotFoundException(String message) {
    super(message);
  }
}
