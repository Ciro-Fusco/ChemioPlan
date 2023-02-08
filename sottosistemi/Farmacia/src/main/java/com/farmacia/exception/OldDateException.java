package com.farmacia.exception;

public class OldDateException extends RuntimeException {
  public OldDateException() {
  }

  public OldDateException(String message) {
    super(message);
  }
}
