package com.farmacia.exception;

/**
 *
 */
public class OrdineAlreadyExistException extends RuntimeException {
  public OrdineAlreadyExistException() {}

  public OrdineAlreadyExistException(String message) {
    super(message);
  }
}
