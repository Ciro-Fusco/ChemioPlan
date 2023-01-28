package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Prenotazione;

/**
 * <p>Questa interfaccia fornisce i metodi di PrenotazioneService.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface IPrenotazioneService {

  Prenotazione[] getAllPrenotazioni();

  Prenotazione getById(String codice);

  String addPrenotazione(Prenotazione prenotazione);

  String updatePrenotazione(Prenotazione prenotazione);

  String deletePrenotazione(String codice);

}

