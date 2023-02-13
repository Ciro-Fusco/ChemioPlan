package com.example.FrontEnd.FrontEnd.service;


import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.Ordine;
import com.example.FrontEnd.FrontEnd.model.OrdineRequest;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @version 0.1
 */
public interface IFarmaciaService {
  /**
   * <p>Questo metodo restituisce un faramco con un determinato Id.</p>
   *
   * @return SchedaFarmaco
   */
  SchedaFarmaco getFarmaco(String id);

  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  SchedaFarmaco[] getAllFarmaci();

  /**
   * <p>Questo metodo restituisce tutti i farmaci disponibili nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  SchedaFarmaco[] getAllFarmaciDisponibili();

  /**
   * <p>Questo metodo inserice una nuova SchedaFarmaco nel database.</p>
   *
   * @param scheda la scheda da inserire
   */
  String addFarmaco(SchedaFarmaco scheda);

  /**
   * <p>Questo metodo inserisce nella SchedaFarmaco un nuovo lotto.</p>
   *
   * @param codice codice della scheda a cui aggiungere il lotto
   * @param lotto il lotto con tutte le nuove informazioni
   *
   */
  String nuovoLotto(String codice, Lotto lotto);

  /**
   * <p>Questo metodo modifica una SchedaFarmaco presente nel database.</p>
   *
   * @param codice il codice della scheda da modificare
   * @param schedaFarmaco la scheda con le modifiche
   */
  String modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco);

  /**
   * <p>Questo metodo fornisce il lotto cercato.</p>
   *
   * @param codiceFarmaco codice del farmaco in cui si cerca il lotto
   * @param numeroLotto lotto da ricercare
   * @return lotto cercato
   */
  Lotto getLotto(String codiceFarmaco, Integer numeroLotto);

  /**
   * <p>Modifca il lotto scelto di un farmaco.</p>
   *
   * @param codiceFarmaco farmaco su cui modificare il lotto
   * @param lotto lotto con le modifiche
   */
  String modificaLotto(String codiceFarmaco, Lotto lotto);

  /**
   * <p>Questo metodo inserisce un nuovo Ordine nel database.</p>
   *
   * @param ordine l'ordine da inserire
   * @return un ordine con l'id autogenerato dal database
   */
  String nuovoOrdine(OrdineRequest ordine);

  /**
   * <p>Questo metodo restituisce tutti gli ordini.</p>
   *
   * @return lista di ordini
   */
  Ordine[] getAllOrdini();

  /**
   * <p>Questo metodo elimina una SchedaFarmaco nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   */
  String eliminaFarmaco(String codice);

  /**
   * <p>Questo metodo elimina una lotto nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   * @param lotto lotto da elimiare
   */
  String eliminaLotto(String codice, Lotto lotto);
}
