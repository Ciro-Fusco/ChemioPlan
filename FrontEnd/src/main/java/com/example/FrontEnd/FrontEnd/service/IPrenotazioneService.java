package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Paziente;
import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import java.util.Date;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @version .0.1
 */
public interface IPrenotazioneService {

  /**
   * <p>Questo metodo viene utilizzato per ottenere tutte le prenotazioni presenti nel Database.</p>
   *
   * @return retistuisce una lista di Prenotazioni
   */
  Prenotazione[] getAllPrenotazioni();

  /**
   * <p>Questo metodo restituisce una prenotazione con un determinato codice.</p>
   *
   * @param codice identificativo della prenotazione
   * @return una prenotazione
   */
  Prenotazione getById(String codice);

  /**
   * <p>Questo metodo restituisce una lista di prenotazioni con una determinata data.</p>
   *
   * @param data data delle prenotazioni
   * @return lista di prenotazione
   */
  Prenotazione[] getByData(Date data);

  /**
   * <p>Questo metodo viene utilizzato per inserire una nuova prenotazione nel Database.</p>
   *
   * @param prenotazione oggetto che rappresenta una prenotazione
   */
  String addPrenotazione(Prenotazione prenotazione);

  /**
   * <p>Questo metodo serve per modificare una prenotazione.</p>.
   *
   * @param prenotazione prenotazione da modificare
   */
  String updatePrenotazione(Prenotazione prenotazione);

  /**
   * <p>Questo metodo viene utilizzato per eliminare una prenotazione dal Database.
   * Cerca una corrispodeza tramite il codice. Se non la trova manda un eccezione. Altrimenti la
   * elimina dal Database.</p>
   *
   * @param codice codice relativo alla prenotazione da eliminare
   */
  String deletePrenotazione(String codice);

  /**
   * <p>Questo metodo va a rideurre la quantità del farmaco destinato a quel paziente
   * dalla quantità di farmaco disponibile e conferma la prenotazione.</p>
   *
   * @param p scheda paziente usata per prendere il codice della prenotazione.
   */
  boolean confermaPrenotazione(SchedaPaziente p);
}

