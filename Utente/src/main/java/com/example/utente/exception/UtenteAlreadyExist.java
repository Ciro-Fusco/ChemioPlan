package com.example.utente.exception;

public class UtenteAlreadyExist extends RuntimeException{
    public UtenteAlreadyExist() {
    }
    public UtenteAlreadyExist(String message) {
        super(message);
    }
}
