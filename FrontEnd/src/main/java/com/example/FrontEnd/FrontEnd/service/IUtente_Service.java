package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUtente_Service {
    Utente getUtente(Integer id);
    Utente[] getUtenti();
    String verificaCrendenziali(Credenziali credenziali);
    String getRuoloByUser(String user);
}
