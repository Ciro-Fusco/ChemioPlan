package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Utente;

import java.util.List;

public interface IUtente_Service {
    Utente getUtente(Integer id);
    Utente[] getUtenti();

}
