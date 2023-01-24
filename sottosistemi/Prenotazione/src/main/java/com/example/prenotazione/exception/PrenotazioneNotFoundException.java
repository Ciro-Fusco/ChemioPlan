package com.example.prenotazione.exception;

/**
 * <p>Questa classe crea un eccezione per una prenotazione non esistente.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public class PrenotazioneNotFoundException extends RuntimeException {

  public PrenotazioneNotFoundException() {
  }

  public PrenotazioneNotFoundException(String message) {
    super(message);
  }
}