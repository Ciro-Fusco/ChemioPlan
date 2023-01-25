package com.farmacia.service;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.exception.OrdineFormatoQuantitaNonCorrettaException;
import com.farmacia.exception.OrdineNotFoundException;
import com.farmacia.exception.SchedaFarmacoAlreadyExistException;
import com.farmacia.exception.SchedaFarmacoNotFoundException;
import com.farmacia.model.Lotto;
import com.farmacia.model.Ordine;
import com.farmacia.model.SchedaFarmaco;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.OrdineRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Questa classe implementa l'interfaccia FarmaciaService.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@Service
@Slf4j
public class FarmaciaServiceImpl implements FarmaciaService {

  /**
   * <p>Riferimento alla repository Farmacia.</p>
   */
  @Autowired
  private FarmaciaRepository repo;

  /**
   * <p>Riferimento alla repository Ordine.</p>
   */
  @Autowired
  private OrdineRepository repoOrdine;

  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  @Override
  public List<SchedaFarmaco> ottieniFarmaci() {
    return repo.findAll();
  }

  /**
   * <p>Questo metodo restituisce un farmaco con uno specifico codice.
   * Se il farmaco non è presente nel database solleva un'eccezione.</p>
   *
   * @param codice codice del farmaco da cercare
   * @return la SchedaFarmaco trovata
   */
  @Override
  public SchedaFarmaco ottieniFarmacoPerCodice(String codice) {
    return repo.findById(codice).orElseThrow(
            () -> new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |" + codice + "| non trovato")
    );
  }

  /**
   * <p>Questo metodo inserice una nuova SchedaFarmaco nel database.
   * Se la scheda è già presente solleva un'eccezione.</p>
   *
   * @param schedaFarmaco la scheda da inserire
   */
  @Override
  public void aggiungiFarmaco(SchedaFarmaco schedaFarmaco) {
    if (repo.existsById(schedaFarmaco.getCodice())) {
      throw new SchedaFarmacoAlreadyExistException("Scheda Farmaco con CODICE: |"
                + schedaFarmaco.getCodice() + "| già esistente");
    }
    repo.save(schedaFarmaco);
  }

  /**
   * <p>Questo metodo modifica una SchedaFarmaco presente nel database.
   * Se la scheda non è presente nel database solleva un'eccezione.</p>
   *
   * @param codice il codice della scheda da modificare
   * @param schedaFarmaco la scheda con le modifiche
   */
  @Override
  public void modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco) {
    var optional = repo.findById(codice);

    if (optional.isEmpty()) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
                + codice + "| non trovato");
    }

    var scheda = optional.get();

    if (schedaFarmaco.getNome() != null) {
      scheda.setNome(schedaFarmaco.getNome());
    }
    repo.save(scheda);
  }

  /**
   * <p>Questo metodo elimina una SchedaFarmaco nel database.
   * Se la scheda non è presente nel database solleva un'eccezione.</p>
   *
   * @param codice il codice della scheda da eliminare
   */
  @Override
  public void eliminaFarmaco(String codice) {
    var optional = repo.findById(codice);

    if (optional.isEmpty()) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
              + codice + "| non trovato");
    }
    repo.deleteById(codice);
  }

  /**
   * <p>Questo metodo inserisce nella SchedaFarmaco un nuovo lotto.
   * Se la scheda non è presente nel database solleva un'eccezione.</p>
   *
   * @param codiceFarmaco codice della scheda a cui aggiungere il lotto
   * @param lotto il lotto con tutte le nuove informazioni
   */
  @Override
  public void nuovoLotto(String codiceFarmaco, Lotto lotto) {
    var optional = repo.findById(codiceFarmaco);

    if (optional.isEmpty()) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
                + codiceFarmaco + "| non trovato");
    }

    var scheda = optional.get();
    if (lotto.getNumeroLotto() != null && lotto.getScadenzaLotto() != null ) {
      scheda.addLotto(lotto);
    }
    repo.save(scheda);
  }

  /**
   * <p>Questo metodo fornisce i lotti di uno speficico farmaco</p>
   *
   * @param codiceFarmaco codice del farmaco
   */
  @Override
  public List<Lotto> ottieniLotti(String codiceFarmaco) {
    var optional = repo.findById(codiceFarmaco);

    if (optional.isEmpty()){
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
              + codiceFarmaco + "| non trovato");
    }
    var scheda = optional.get();
    return scheda.getLotti();
  }

  /**
   * <p>Questo metodo inserisce un nuovo Ordine nel database.
   * Se il farmaco associato all'ordine non è presente nel database
   * solleva un'eccezione.</p>
   *
   * @param ordine l'ordine da inserire
   * @return un ordine con l'id autogenerato dal database
   */
  @Override
  public Ordine nuovoOrdine(OrdineRequest ordine) {
    if (!repo.existsById(ordine.getCodiceFarmaco())) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con Codice "
                + ordine.getCodiceFarmaco() + " non trovata");
    }

    if (ordine.getQuantita() == null || ordine.getQuantita() < 0) {
      throw new OrdineFormatoQuantitaNonCorrettaException("Formato quantità errato "
                + ordine.getQuantita());
    }

    Ordine o = Ordine.builder()
            .codiceFarmaco(ordine.getCodiceFarmaco())
            .quantita(ordine.getQuantita())
            .stato(Ordine.STATO_DA_ACQUISTARE)
            .build();
    repoOrdine.save(o);
    log.info("Ordine {} salvato", o.getId());
    return o;
  }

  /**
   * <p>Questo metodo restituisce un Ordine con uno specifico id.
   * Se l'ordine non è presente nel database solleva un'eccezione.</p>
   *
   * @param id l'id dell'ordine da cercare
   * @return l'ordine con l'id
   */
  @Override
  public Ordine ottieniOrdine(String id) {
    return repoOrdine.findById(id).orElseThrow(
            () -> new OrdineNotFoundException("Ordine con ID: |" + id + "| non trovato")
    );
  }

  /**
   * <p>Questo metodo modifica lo stato dell'Ordine.
   * Se l'ordine non è presente nel database solleva un'eccezione.</p>
   *
   * @param id l'id dell'ordine da modificare
   */
  @Override
  public void ordineConsegnato(String id) {
    var ordine = repoOrdine.findById(id);

    if (ordine.isEmpty()) {
      throw new OrdineNotFoundException("Ordine " + id + " non trovato");
    }

    Ordine o = ordine.get();
    o.setStato(Ordine.STATO_CONSEGNATO);
    repoOrdine.save(o);
  }
}
