package com.example.utente.service;

import com.example.utente.model.Utente;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * <p>Interfaccia che definisce i metodi per l'entità Utente.</p>
 *
 * @author chris
 * @version 1.0
 */
public interface IutenteService {
  /**
  * <p>Questo metodo restituisce tutti gli utenti presenti nel Database.</p>
  *
  * @return una lista di utenti
  * @since 1.0
  */
  List<Utente> getAllUtenti();

  /**
   * <p>Questo metodo restituisce un utente in base all'id.</p>
   *
   * @param id dell'utente da cercare
   * @return un utente con un id specifico
   * @since 1.0
  */
  Utente getUtenteById(Integer id);

  /**
   * <p>Elimina un utente in base all'id.</p>
   *
   * @param id dell'utente da eliminare
   * @since 1.0
  */
  void deleteUtente(Integer id);

  /**
   * <p>Modifica un utente in base all'id con delle nuove informazioni.</p>
   *
   * @param id dell'utente da modificare
   * @param u contiene le informazioni dell'utente da aggiornare
   * @since 1.0
  */
  void updateUtente(Integer id, Utente u);

  /**
   * <p>Inserisce un nuovo utente nel Database.</p>
   *
   * @param u Utente da inserire nel Database
   * @since 1.0
  */
  void addUtente(Utente u);

  /**
   * <p>Verifica se le credenziali nel Database coincidono.</p>
   *
   * @param user Username dell'utente
   * @param pass Password dell'utente
   * @since 1.0
  */
  ResponseEntity<?> verificaCredenziali(String user, String pass);

  String getRuoloByUser(String user);
}
