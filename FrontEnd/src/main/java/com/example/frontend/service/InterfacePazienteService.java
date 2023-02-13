package com.example.frontend.service;

import com.example.frontend.model.SchedaPaziente;
import java.util.HashMap;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @version 0.1
 */
public interface InterfacePazienteService {

  /**
   * <p> Questo metodo restituisce tutti i Pazienti presenti nel DB.</p>
   */
  SchedaPaziente[] getPazienti();

  /**
   * <p> Questo metodo restituisce un Paziente con un determinato codiceFiscale.</p>
   *
   * @param cf il CodiceFiscale della schedaPaziente da cercare
   * @return la schedaPaziente trovata
   */
  SchedaPaziente getPaziente(String cf);

  /**
   * <p> Questo metodo inserisce un nuovo Paziente nel DB.</p>
   *
   * @param paziente la schedaPaziente da inserire
   */
  String addPaziente(SchedaPaziente paziente);

  /**
   * <p>Questo metodo modifica un Paziente presente nel DB.</p>
   *
   * @param scheda la schedaPaziente con le modifiche
   */
  String modificaPaziente(SchedaPaziente scheda);

  /**
   * <p> Questo metodo elimina Paziente presente nel DB.</p>
   *
   * @param cf il codiceFiscale della schedaPaziente da eliminare
   */
  String eliminaPaziente(String cf);

  /**
   * <p> Questo metodo restituisce i farmaci con il
   * relativo dosaggio di un Paziente con un determinato codiceFiscale.</p>
   *
   * @param cf il CodiceFiscale della schedaPaziente da cercare
   */
  HashMap<String, Double> getFarmaci(String cf);

  /**
   * <p> Questo metodo restituisce un Paziente in base ad un ricerca
   * con dei determinati filtri.</p>
   *
   * @param filtri il CodiceFiscale della schedaPaziente da cercare
   */
  SchedaPaziente[] getPazientiByFiltri(SchedaPaziente filtri);
}
