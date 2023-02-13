package com.chemioplan.SchedaPaziente.service;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */

public interface SchedaPazienteServiceInterface {
  /**
   * <p> Questo metodo restituisce tutte le schedePaziente presenti nel DB.</p>
   */
  List<SchedaPaziente> ottieniSchedePazienti();


  /**
   * <p> Questo metodo restituisce una schedaPaziente con un determinato codiceFiscale.</p>
   *
   * @param codiceFiscale il CodiceFiscale della schedaPaziente da cercare
   * @return la schedaPaziente trovata
   */
  SchedaPaziente ottieniSchedaPazientePerCodiceFiscale(String codiceFiscale);

  /**
   * <p> Questo metodo inserisce una nuova schedaPaziente nel DB.</p>
   *
   * @param schedaPaziente la schedaPaziente da inserire
   */
  void aggiungiSchedaPaziente(SchedaPaziente schedaPaziente);

  /**
   * <p>Questo metodo modifica una schedaPaziente presente nel DB.</p>
   *
   * @param codiceFiscale  l'id della schedaPaziente da modificare
   * @param schedaPaziente la schedaPaziente con le modifiche
   */
  void modificaSchedaPaziente(String codiceFiscale, SchedaPaziente schedaPaziente);

  /**
   * <p> Questo metodo elimina una schedaPaziente presente nel DB.</p>
   *
   * @param codiceFiscale il codiceFiscale della schedaPaziente da eliminare
   */
  void eliminaSchedaPaziente(String codiceFiscale);

  /**
   * <p>Questo metodo resituisce i farmaci ed il dosaggio relativo ad un certo codiceFiscale.</p>
   *
   * @param codiceFiscale il codiceFiscale del paziente
   * @return la mappa dei codici e dosaggio
   */
  HashMap<String, Double> ottieniFarmaciPerCodiceFiscale(String codiceFiscale);

  /**
   * <p>Metodo che restituisce una lista di pazienti in base ai filtri dati.</p>
   *
   * @param paziente paziente che contiene tutti i filtri
   * @return lista di pazienti trovati
   */
  List<SchedaPaziente> ottieniSchedePazientiByPaziente(SchedaPaziente paziente);
}
