package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per un lotto già esistente.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class LottoAlreadyExistException extends RuntimeException {
  public LottoAlreadyExistException() {
  }

  public LottoAlreadyExistException(String message) {
    super(message);
  }
}
