package com.example.utente.exception;

public class CredenzialiNonValide extends RuntimeException{
    public CredenzialiNonValide() {
    }

    public CredenzialiNonValide(String message) {
        super(message);
    }
}
