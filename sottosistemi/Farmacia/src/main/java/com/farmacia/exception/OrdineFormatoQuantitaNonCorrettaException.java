package com.farmacia.exception;

/**
 * <p>Questa classe crea eccezioni per il formato della quantità dell'ordine non corretto.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public class OrdineFormatoQuantitaNonCorrettaException extends RuntimeException {
  public OrdineFormatoQuantitaNonCorrettaException() {}

  public OrdineFormatoQuantitaNonCorrettaException(String message) {
    super(message);
  }
}
