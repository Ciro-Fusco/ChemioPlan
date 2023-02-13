package com.chemioplan.schedapaziente.exception;

/**
 * <p>Questa classe crea un eccezione per una scheda paziente già esistente.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */
public class SchedaPazienteAlredyExistException extends RuntimeException {
  public SchedaPazienteAlredyExistException(String message) {
    super(message);
  }
}
