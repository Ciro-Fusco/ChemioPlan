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
 *
 *
 */
@Service
@Slf4j
public class FarmaciaServiceImpl implements FarmaciaService {

  @Autowired
  private FarmaciaRepository repo;

  @Autowired
  private OrdineRepository repoOrdine;

  @Override
  public List<SchedaFarmaco> ottieniFarmaci() {
    return repo.findAll();
  }

  @Override
  public SchedaFarmaco ottieniFarmacoPerCodice(String codice) {
    return repo.findById(codice).orElseThrow(
            () -> new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |" + codice + "| non trovato")
    );
  }

  @Override
  public void aggiungiFarmaco(SchedaFarmaco schedaFarmaco) {
    if (repo.existsById(schedaFarmaco.getCodice())) {
      throw new SchedaFarmacoAlreadyExistException("Scheda Farmaco con CODICE: |"
                + schedaFarmaco.getCodice() + "| già esistente");
    }
    repo.save(schedaFarmaco);
  }

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

    if (schedaFarmaco.getQuantita() != null || schedaFarmaco.getQuantita() >= 0) {
      scheda.setQuantita(schedaFarmaco.getQuantita());
    }

    if (schedaFarmaco.getNumeroLotto() != null) {
      scheda.setNumeroLotto(schedaFarmaco.getNumeroLotto());
    }

    if (schedaFarmaco.getScadenzaLotto() != null) {
      scheda.setScadenzaLotto(schedaFarmaco.getScadenzaLotto());
    }

    repo.save(scheda);
  }

  @Override
  public void eliminaFarmaco(String codice) {
    var optional = repo.findById(codice);

    if (optional.isEmpty()) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
              + codice + "| non trovato");
    }
    repo.deleteById(codice);
  }

  @Override
  public void nuovoLotto(Lotto lotto) {
    var optional = repo.findById(lotto.getCodiceFarmaco());

    if (optional.isEmpty()) {
      throw new SchedaFarmacoNotFoundException("Scheda Farmaco con CODICE: |"
                + lotto.getCodiceFarmaco() + "| non trovato");
    }

    var scheda = optional.get();
    if (lotto.getNumeroLotto() != null) {
      scheda.setNumeroLotto(lotto.getNumeroLotto());
    }

    if (lotto.getScadenzaLotto() != null) {
      scheda.setScadenzaLotto(lotto.getScadenzaLotto());
    }

    repo.save(scheda);
  }

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

  @Override
  public Ordine ottieniOrdine(String id) {
    return repoOrdine.findById(id).orElseThrow(
            () -> new OrdineNotFoundException("Scheda Farmaco con ID: |" + id + "| non trovato")
    );
  }

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
