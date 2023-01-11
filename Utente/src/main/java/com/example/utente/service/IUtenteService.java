package com.example.utente.service;

import com.example.utente.model.Utente;

import java.util.List;

public interface IUtenteService {
    List<Utente> getAllUtenti();
    Utente getUtenteById(Integer id);
    void deleteUtente(Integer id);
    void updateUtente(Integer id, Utente u);
    void addUtente(Utente u);
    void verificaCredenziali(String user,String pass);
}
