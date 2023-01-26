package com.farmacia.exception;

public class LottoNotFoundException extends RuntimeException {
  public LottoNotFoundException() {
  }

  public LottoNotFoundException(String message) {
    super(message);
  }
}
