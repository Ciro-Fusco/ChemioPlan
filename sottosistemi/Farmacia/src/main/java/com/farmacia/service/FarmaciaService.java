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
   * <p>Questo metodo restituisce tutti i farmaci disponibili,
   * con quantità di alemeno un lotto maggiore 0 presenti nel database.</p>
   *
   * @return lista di farmaci
   */
  List<SchedaFarmaco> ottieniFarmaciDisponibili();

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
   * <p>Questo metodo inserisce nella SchedaFarmaco un nuovo lotto.</p>
   *
   * @param codiceFarmaco codice della scheda a cui aggiungere il lotto
   * @param lotto il lotto con tutte le nuove informazioni
   *
   */
  void nuovoLotto(String codiceFarmaco, Lotto lotto);

  /**
   * <p>Questo metodo fornisce i lotti di uno speficico farmaco.</p>
   *
   * @param codiceFarmaco codice del farmaco
   * @return lista dei lotti del farmaco
   */
  List<Lotto> ottieniLotti(String codiceFarmaco);

  /**
   * <p>Questo metodo fornisce il lotto cercato.</p>
   *
   * @param codiceFarmaco codice del farmaco in cui si cerca il lotto
   * @param numeroLotto lotto da ricercare
   * @return lotto cercato
   */
  Lotto ottieniLotto(String codiceFarmaco, Integer numeroLotto);

  /**
   * <p>Modifca il lotto scelto di un farmaco.</p>
   *
   * @param codiceFarmaco farmaco su cui modificare il lotto
   * @param numeroLotto numero del lotto da modificare
   * @param lotto lotto con le modifiche
   */
  void modificaLotto(String codiceFarmaco, Integer numeroLotto, Lotto lotto);

  /**
   * <p>Questo metodo elimina un lotto da una scheda farmaco.</p>
   *
   * @param codice codice del farmaco
   * @param lotto lotto da eliminare
   */
  void eliminaLotto(String codice, Lotto lotto);

  /**
   * <p>Questo metodo inserisce un nuovo Ordine nel database.</p>
   *
   * @param ordine l'ordine da inserire
   * @return un ordine con l'id autogenerato dal database
   */
  Ordine nuovoOrdine(OrdineRequest ordine);

  /**
   * <p>Questo metodo restituisce tutti gli ordini.</p>
   *
   * @return lista di ordini
   */
  List<Ordine> ottieniOrdini();

  /**
   * <p>Questo metodo modifica lo stato dell'Ordine.</p>
   *
   * @param id l'id dell'ordine da modificare
   */
  void ordineConsegnato(String id);

  /**
   * <p>Questo metodo fornisce l'ordine cercato.</p>
   *
   * @param id id dell'ordine da ricercare
   * @return ordine trovato
   */
  Ordine ottieniOrdine(String id);
}
