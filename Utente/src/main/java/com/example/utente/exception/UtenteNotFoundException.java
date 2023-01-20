package com.example.utente.exception;

/**
 * <p>Questa classe genera eccezioni per un utente non trovato.</p>
 *
 * @author chris
 * @version  1.0
 */
public class UtenteNotFoundException extends RuntimeException {
  public UtenteNotFoundException() {
  }

  /**
   * <p>Stampa il messaggio di errore per un utente non trovato.</p>
   *
   * @param message messaggio di errore da stampare
   * @since 1.0
  */
  public UtenteNotFoundException(String message) {
    super(message);
  }
}
