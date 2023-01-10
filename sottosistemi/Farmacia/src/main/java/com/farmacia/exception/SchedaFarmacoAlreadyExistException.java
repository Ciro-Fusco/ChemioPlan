package com.farmacia.exception;

public class SchedaFarmacoAlreadyExistException extends RuntimeException {
  public SchedaFarmacoAlreadyExistException() {}

  public SchedaFarmacoAlreadyExistException(String message) {
    super(message);
  }
}
