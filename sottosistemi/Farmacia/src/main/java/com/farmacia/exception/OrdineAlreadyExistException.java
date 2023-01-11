package com.farmacia.exception;

/**
 * <p>Questa classe crea un eccezione per un ordine già esistente.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class OrdineAlreadyExistException extends RuntimeException {
  public OrdineAlreadyExistException() {}

  public OrdineAlreadyExistException(String message) {
    super(message);
  }
}
