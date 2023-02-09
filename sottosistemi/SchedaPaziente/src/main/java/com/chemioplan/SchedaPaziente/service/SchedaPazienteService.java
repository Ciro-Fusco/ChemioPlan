package com.chemioplan.SchedaPaziente.service;

import com.chemioplan.SchedaPaziente.exception.SchedaPazienteAlredyExistException;
import com.chemioplan.SchedaPaziente.exception.SchedaPazienteNotFoundException;
import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import com.chemioplan.SchedaPaziente.repository.SchedaPazienteRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>Questa classe implementa l'interfaccia SchedaPazienteServiceInterface.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */

@Service

public class SchedaPazienteService implements SchedaPazienteServiceInterface {

  /**
   * <p>Riferimento alla repository SchedaPazienteRepository.</p>
   */
  @Autowired
  private SchedaPazienteRepository schedaPazienteRepository;

  /**
   * <p>Questo metodo restituisce tutti le schedePazienti presenti nel database.</p>
   *
   * @return lista di SchedaPazienti
   */
  @Override
  public List<SchedaPaziente> ottieniSchedePazienti() {
    return schedaPazienteRepository.findAll();
  }


  /**
   * <p> Questo metodo restituisce una schedaPaziente con un determinato codiceFiscale.</p>
   *
   * @param codiceFiscale il CodiceFiscale della schedaPaziente da cercare
   * @return la schedaPaziente trovata
   */
  @Override
  public SchedaPaziente ottieniSchedaPazientePerCodiceFiscale(String codiceFiscale) {
    var optional = schedaPazienteRepository.findById(codiceFiscale);
    if (optional.isEmpty()) {
      throw new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale "
              + codiceFiscale + " non trovata");
    }
    return optional.get();
  }

  /**
   * <p> Questo metodo inserisce una nuova schedaPaziente nel DB.</p>
   *
   * @param schedaPaziente la schedaPaziente da inserire
   */
  @Override
  public void aggiungiSchedaPaziente(SchedaPaziente schedaPaziente) {
    if (schedaPazienteRepository.existsById(schedaPaziente.getCodiceFiscale())) {
      throw new SchedaPazienteAlredyExistException("Scheda Paziente con codiceFiscale: |"
              + schedaPaziente.getCodiceFiscale() + " | già esistente");
    }
    schedaPazienteRepository.save(schedaPaziente);
  }

  /**
   * <p>Questo metodo modifica una schedaPaziente presente nel DB.</p>
   *
   * @param codiceFiscale  il codiceFiscale della schedaPaziente da modificare
   * @param schedaPaziente la schedaPaziente con le modifiche
   */
  @Override
  public void modificaSchedaPaziente(String codiceFiscale, SchedaPaziente schedaPaziente) {
    var optional = schedaPazienteRepository.findById(codiceFiscale);
    if (optional.isEmpty()) {
      throw new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale "
              + codiceFiscale + " non trovata");
    }
    var scheda = optional.get();
    if (schedaPaziente.getFarmaci() != null) {
      scheda.setFarmaci(schedaPaziente.getFarmaci());
    }
    if (schedaPaziente.getMalattie() != null) {
      scheda.setMalattie(schedaPaziente.getMalattie());
      schedaPazienteRepository.save(scheda);
    }
  }

  /**
   * <p> Questo metodo elimina una schedaPaziente presente nel DB.</p>
   *
   * @param codiceFiscale il codiceFiscale della schedaPaziente da eliminare
   */
  @Override
  public void eliminaSchedaPaziente(String codiceFiscale) {
    var optional = schedaPazienteRepository.findById(codiceFiscale);
    if (optional.isEmpty()) {
      throw new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale "
              + codiceFiscale + " non trovata");
    }
    schedaPazienteRepository.deleteById(codiceFiscale);


  }

  /**
   * <p>Questo metodo resituisce i farmaci ed il dosaggio relativo ad un certo codiceFiscale.</p>
   *
   * @param codiceFiscale il codiceFiscale del paziente
   * @return la mappa dei codici e dosaggio
   */
  @Override
  public HashMap<String, Double> ottieniFarmaciPerCodiceFiscale(String codiceFiscale) {
    var optional = schedaPazienteRepository.findById(codiceFiscale);
    if (optional.isEmpty()) {
      throw new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale "
              + codiceFiscale + " non trovata");
    }
    return optional.get().getFarmaci();
  }

  /**
   * <p>Metodo che restituisce una lista di pazienti in base ai filtri dati.</p>
   *
   * @param paziente paziente che contiene tutti i filtri
   * @return lista di pazienti trovati
   */
  @Override
  public List<SchedaPaziente> ottieniSchedePazientiByPaziente(SchedaPaziente paziente) {
    //caso in cui il codice fiscale è stato inserito
    if (paziente.getCodiceFiscale() != null && paziente.getCodiceFiscale() != "") {
      List<SchedaPaziente> pazienti = new ArrayList<>();
      pazienti.add(this.ottieniSchedaPazientePerCodiceFiscale(paziente.getCodiceFiscale()));
      return pazienti;
    }

    //ricerca per nome cognome e farmaci
    if (paziente.getNome() != null && paziente.getCognome() != null && paziente.getFarmaci() != null) {
      if (paziente.getNome() != "" && paziente.getCognome() != "" && paziente.getFarmaci().size() > 0) {
        List<String> codici = paziente.getFarmaci().keySet().stream().toList();
        String c = codici.get(0);
        List<SchedaPaziente> p = schedaPazienteRepository.findByNomeAndCognomeAndFarmaci(
                  paziente.getNome(), paziente.getCognome(), c);
        return p;
        }
    }

    //ricerca per nome e farmaci
    if (paziente.getNome() != null && paziente.getFarmaci() != null) {
      if (paziente.getNome() != "" &&  paziente.getFarmaci().size() > 0) {
        List<String> codici = paziente.getFarmaci().keySet().stream().toList();
        String c = codici.get(0);
        List<SchedaPaziente> p = schedaPazienteRepository.findByNomeAndFarmaci(
                paziente.getNome(), c);
        return p;
      }
    }

    if (paziente.getCognome() != null && paziente.getFarmaci() != null) {
      if (paziente.getCognome() != "" &&  paziente.getFarmaci().size() > 0) {
        List<String> codici = paziente.getFarmaci().keySet().stream().toList();
        String c = codici.get(0);
        List<SchedaPaziente> p = schedaPazienteRepository.findByCognomeAndFarmaci(
                paziente.getCognome(), c);
        return p;
      }
    }

    if (paziente.getNome() != null && paziente.getCognome() != null) {
      if (paziente.getNome() != "" && paziente.getCognome() != "") {
        return schedaPazienteRepository.findByNomeAndAndCognome(paziente.getNome(), paziente.getCognome());
      }
    }

    if (paziente.getNome() != null) {
      if (paziente.getNome() != "") {
        return schedaPazienteRepository.findByNome(paziente.getNome());
      }
    }

    if (paziente.getCognome() != null) {
      if (paziente.getCognome() != "") {
        return schedaPazienteRepository.findByCognome(paziente.getCognome());
      }
    }

    if (paziente.getFarmaci() != null) {
      if (paziente.getFarmaci().size() > 0 ) {
        List<String> codici = paziente.getFarmaci().keySet().stream().toList();
        String c = codici.get(0);
        List<SchedaPaziente> p = schedaPazienteRepository.findByFarmaco(c);
        return p;
      }
    }

    return schedaPazienteRepository.findAll();
  }
}
