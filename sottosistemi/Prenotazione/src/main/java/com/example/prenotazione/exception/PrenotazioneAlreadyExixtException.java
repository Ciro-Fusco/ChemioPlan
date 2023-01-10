package com.example.prenotazione.exception;

/**
 * <p>Classe per gestire una particolare eccezione.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public class PrenotazioneAlreadyExixtException extends RuntimeException {

  public PrenotazioneAlreadyExixtException() {
  }

  public PrenotazioneAlreadyExixtException(String message) {
    super(message);
  }
}
