package com.farmacia.exception;

public class SchedaFarmacoNotFoundException extends RuntimeException {
  public SchedaFarmacoNotFoundException() {}

  public SchedaFarmacoNotFoundException(String message) {
    super(message);
  }
}