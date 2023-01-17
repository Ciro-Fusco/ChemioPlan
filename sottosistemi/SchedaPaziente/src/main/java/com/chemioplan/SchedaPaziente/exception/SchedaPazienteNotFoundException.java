package com.chemioplan.SchedaPaziente.exception;

public class SchedaPazienteNotFoundException extends RuntimeException {
    public SchedaPazienteNotFoundException() {
    }

    public SchedaPazienteNotFoundException(String message) {
        super(message);
    }
}
