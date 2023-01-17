package com.example.utente.exception;

/**
 * <p>Questa classe crea eccezioni per quando le credenziali non sono valide.</p>
 *
 * @author chris
 * @version 1.0
 */
public class CredenzialiNonValideException extends RuntimeException {
    public CredenzialiNonValideException() {
    }

    /**
     * <p>Stampa il messaggio di errore per le crendenziali non valide.</p>
     *
     *
     * @param message messaggio di errore da stampare
     * @since 1.0
     */
    public CredenzialiNonValideException(String message) {
        super(message);
    }
}
