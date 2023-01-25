package com.farmacia.exception;

public class LottoAlreadyExistException extends RuntimeException {
  public LottoAlreadyExistException() {
  }

  public LottoAlreadyExistException(String message) {
    super(message);
  }
}
