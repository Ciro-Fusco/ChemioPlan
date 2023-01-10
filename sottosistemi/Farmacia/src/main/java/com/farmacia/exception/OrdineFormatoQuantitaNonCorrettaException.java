package com.farmacia.exception;

public class OrdineFormatoQuantitaNonCorrettaException extends RuntimeException {
  public OrdineFormatoQuantitaNonCorrettaException() {}

  public OrdineFormatoQuantitaNonCorrettaException(String message) {
    super(message);
  }
}
