package com.chemioplan.SchedaPaziente.exception;

/**
 * <p>Questa classe crea un eccezione per una scheda paziente non trovata.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */
public class SchedaPazienteNotFoundException extends RuntimeException {
  public SchedaPazienteNotFoundException() {
  }

  public SchedaPazienteNotFoundException(String message) {
    super(message);
  }
}
