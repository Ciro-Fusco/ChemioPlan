package com.example.utente.exception;

/**
 * <p>Questa classe crea eccezioni per un utente già esistenze.</p>
 *
 * @author chris
 * @version 1.0
 */
public class UtenteAlreadyExistException extends RuntimeException {
  public UtenteAlreadyExistException() {
  }

  /**
   * <p>Stampa il messaggio di errore passato in input.</p>
   *
   * @param message messaggio di errore
   * @since 1.0
  */
  public UtenteAlreadyExistException(String message) {
    super(message);
  }
}
