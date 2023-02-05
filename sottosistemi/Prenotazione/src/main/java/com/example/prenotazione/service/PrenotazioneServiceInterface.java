package com.example.prenotazione.service;

import com.example.prenotazione.dto.PrenotazioneRequest;
import com.example.prenotazione.dto.PrenotazioneResponse;
import com.example.prenotazione.model.Prenotazione;
import java.util.List;


/**
 * <p>Questa interfaccia fornisce i metodi di PrenotazioneService.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface PrenotazioneServiceInterface {

  void addPrenotazione(PrenotazioneRequest prenotazioneRequest);

  List<PrenotazioneResponse> getAllPrenotazioni();

  void updatePrenotazione(String codice, Prenotazione prenotazione);

  void deletePrenotazione(String codice);

  PrenotazioneResponse getById(String codice);


}

