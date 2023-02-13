package com.example.frontend.service;

import com.example.frontend.model.Credenziali;
import com.example.frontend.model.Utente;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @version 0.1
 */
public interface InterfaceUtenteService {
  /**
   * <p>Questo metodo restituisce un utente con un determinato id.</p>
   *
   * @param id id dell'utente
   */
  Utente getUtente(Integer id);

  /**
   * <p>Questo metodo restituisce tutti gli utenti presenti nel Database.</p>
   *
   * @return una lista di utenti
   */
  Utente[] getUtenti();

  /**
   * <p>Verifica se le credenziali nel Database coincidono con quelle inserite.</p>
   *
   * @param credenziali credenzili dell'utente
   */
  String verificaCrendenziali(Credenziali credenziali);

  /**
   * <p>Ricerca il ruolo dell'utente in base allo username.</p>
   *
   * @param user username dell'utente
   * @return ruolo dell'utente
   */
  String getRuoloByUser(String user);

  /**
   * <p>Inserisce un nuovo utente nel Database.</p>
   *
   * @param utente Utente da inserire nel Database
   */
  String aggiungiUtente(Utente utente);

  /**
   * <p>Modifica un utente in base all'id con delle nuove informazioni.</p>
   *
   * @param id dell'utente da modificare
   * @param utente contiene le informazioni dell'utente da aggiornare
   * @since 1.0
   */
  String modificaUtente(Integer id, Utente utente);

  /**
   * <p>Elimina un utente in base all'id.</p>
   *
   * @param id dell'utente da eliminare
   */
  String elimina(Integer id);
}
