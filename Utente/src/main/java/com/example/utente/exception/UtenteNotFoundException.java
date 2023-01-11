package com.example.utente.exception;

public class UtenteNotFoundException extends RuntimeException{
    public UtenteNotFoundException() {
    }
    public UtenteNotFoundException(String message) {
        super(message);
    }
}
