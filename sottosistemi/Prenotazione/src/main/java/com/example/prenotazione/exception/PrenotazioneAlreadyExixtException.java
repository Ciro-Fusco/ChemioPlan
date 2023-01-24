package com.example.prenotazione.exception;

/**
 * <p>Questa classe crea un eccezione per una prenotazione già esistente.</p>
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
