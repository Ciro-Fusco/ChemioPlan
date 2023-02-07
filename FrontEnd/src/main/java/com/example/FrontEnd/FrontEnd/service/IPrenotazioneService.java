package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Paziente;
import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;

import java.util.Date;

/**
 * <p>Questa interfaccia fornisce i metodi di PrenotazioneService.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface IPrenotazioneService {

  Prenotazione[] getAllPrenotazioni();

  Prenotazione getById(String codice);

  Prenotazione[] getByData(Date data);

  String addPrenotazione(Prenotazione prenotazione);

  String updatePrenotazione(Prenotazione prenotazione);

  String deletePrenotazione(String codice);

  boolean confermaPrenotazione(SchedaPaziente p);
}

