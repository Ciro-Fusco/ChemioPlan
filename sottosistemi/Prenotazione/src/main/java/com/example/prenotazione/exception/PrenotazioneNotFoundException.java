package com.example.prenotazione.exception;

/**
 * <p>Classe per gestire una particolare eccezione.</p>
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