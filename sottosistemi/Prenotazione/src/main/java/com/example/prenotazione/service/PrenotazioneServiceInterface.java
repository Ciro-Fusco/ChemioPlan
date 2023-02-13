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

  /**
   * <p>Questo metodo viene utilizzato per inserire una nuova prenotazione nel Database.</p>
   *
   * @param prenotazioneRequest prenotazione da aggiungere
   */
  void addPrenotazione(PrenotazioneRequest prenotazioneRequest);

  /**
   * <p>Questo metodo viene utilizzato per ottenere tutte le prenotazioni presenti nel Database.</p>
   *
   * @return retistuisce una lista di Prenotazioni
   */
  List<PrenotazioneResponse> getAllPrenotazioni();

  /**
   * <p>Questo metodo serve per modificare una prenotazione.</p>.
   *
   * @param prenotazione prenotazione da modificare
   */
  void updatePrenotazione(String codice, Prenotazione prenotazione);

  /**
   * <p>Questo metodo elimina una Prenotazione nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   */
  void deletePrenotazione(String codice);

  /**
   * <p>Questo metodo restituisce una prenotazione con un determinato codice.</p>
   *
   * @param codice identificativo della prenotazione
   * @return una prenotazione
   */
  PrenotazioneResponse getById(String codice);


}

