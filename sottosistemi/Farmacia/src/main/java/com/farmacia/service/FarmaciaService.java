package com.farmacia.service;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.model.Lotto;
import com.farmacia.model.Ordine;
import com.farmacia.model.SchedaFarmaco;
import java.util.List;

/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public interface FarmaciaService {
  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  List<SchedaFarmaco> ottieniFarmaci();

  /**
   * <p>Questo metodo restituisce un farmaco con uno specifico codice.</p>
   *
   * @param codice codice del farmaco da cercare
   * @return la SchedaFarmaco trovata
   */
  SchedaFarmaco ottieniFarmacoPerCodice(String codice);

  /**
   * <p>Questo metodo inserice una nuova SchedaFarmaco nel database.</p>
   *
   * @param schedaFarmaco la scheda da inserire
   */
  void aggiungiFarmaco(SchedaFarmaco schedaFarmaco);

  /**
   * <p>Questo metodo modifica una SchedaFarmaco presente nel database.</p>
   *
   * @param codice il codice della scheda da modificare
   * @param schedaFarmaco la scheda con le modifiche
   */
  void modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco);

  /**
   * <p>Questo metodo elimina una SchedaFarmaco nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   */
  void eliminaFarmaco(String codice);

  /**
   * <p>Questo metodo modifica una SchedaFarmaco nel database con un nuovo lotto.</p>
   *
   * @param lotto il lotto con tutte le nuove informazioni
   */
  void nuovoLotto(Lotto lotto);

  /**
   * <p>Questo metodo inserisce un nuovo Ordine nel database.</p>
   *
   * @param ordine l'ordine da inserire
   * @return un ordine con l'id autogenerato dal database
   */
  Ordine nuovoOrdine(OrdineRequest ordine);

  /**
   * <p>Questo metodo restituisce un Ordine con uno specifico id.</p>
   *
   * @param id l'id dell'ordine da cercare
   * @return l'ordine con l'id
   */
  Ordine ottieniOrdine(String id);

  /**
   * <p>Questo metodo modifica lo stato dell'Ordine.</p>
   *
   * @param id l'id dell'ordine da modificare
   */
  void ordineConsegnato(String id);
}
