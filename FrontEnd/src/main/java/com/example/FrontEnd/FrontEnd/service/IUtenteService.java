package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUtenteService {
    Utente getUtente(Integer id);
    Utente[] getUtenti();
    String verificaCrendenziali(Credenziali credenziali);
    String getRuoloByUser(String user);
    String aggiungiUtente(Utente utente);

  String modificaUtente(Integer id, Utente utente);

  String elimina(Integer id);
}
