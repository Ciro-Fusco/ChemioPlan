package com.farmacia.exception;

public class OrdineNotFoundException extends RuntimeException {
  public OrdineNotFoundException() {
  }

  public OrdineNotFoundException(String message) {
    super(message);
  }
}
