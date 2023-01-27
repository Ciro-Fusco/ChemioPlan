package com.example.prenotazione.service;


import com.example.prenotazione.dto.PrenotazioneRequest;
import com.example.prenotazione.dto.PrenotazioneResponse;
import com.example.prenotazione.exception.PrenotazioneNotFoundException;
import com.example.prenotazione.model.Prenotazione;
import com.example.prenotazione.model.SchedaPaziente;
import com.example.prenotazione.repository.PrenotazioneRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



/**
 * <p>Questa interfaccia fornisce le funzionalità per il controller.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Service
public class PrenotazioneService  implements  PrenotazioneServiceInterface {

  @Autowired
  private PrenotazioneRepository prenotazioneRepository;

  /**
   * <p>Questo metodo viene utilizzato per trasformaare un oggetto 'Prenotazione' in un oggetto
   * PrenotazioneResponse.</p>
   *
   * @param prenotazione oggetto che rappresenta una prenotazione
   * @return restituisce l'oggetto prenotazione trasformato in un oggetto prenotazioneResponse
   */
  private PrenotazioneResponse mapToPrenotazioneResponse(Prenotazione prenotazione) {
    return PrenotazioneResponse.builder().codiceFiscale(prenotazione.getCodiceFiscale())
        .codice(prenotazione.getCodice())
        .data(prenotazione.getData()).sala(prenotazione.getSala())
        .poltrona(prenotazione.getPoltrona()).codiceFarmaci(prenotazione.getCodiceFarmaci())
        .build();
  }

  /**
   * <p>Questo metodo viene utilizzato per inserire una nuova prenotazione nel Database. Crea un
   * oggetto prenotazione a partire da un oggetto PrenotazioneRequest e lo inserisce nel
   * Database.</p>
   *
   * @param prenotazioneRequest oggetto che rappresenta una prenotazione
   */
  @Override
  public void addPrenotazione(PrenotazioneRequest prenotazioneRequest) {
    RestTemplate restTemplate = new RestTemplate();
    String uri = "http://localhost:8084/pazienti/";
    SchedaPaziente  response = restTemplate.getForObject(
        uri + prenotazioneRequest.getCodiceFiscale(), SchedaPaziente.class);

    Prenotazione p = Prenotazione.builder().codiceFiscale(prenotazioneRequest.getCodiceFiscale())
        .data(prenotazioneRequest.getData())
        .sala(prenotazioneRequest.getSala()).poltrona(prenotazioneRequest.getPoltrona())
        .codiceFarmaci(prenotazioneRequest.getCodiceFarmaci()).build();
    prenotazioneRepository.insert(p);
  }

  /**
   * <p>Questo metodo viene utilizzato per ottenere tutte le prenotazioni presenti nel Database
   * Prende tutte le prenotazioni, le trasforma nel tipo PrenotazioneResponse.</p>
   *
   * @return retistuisce una lista di PrenotazioniResponse
   */
  @Override
  public List<PrenotazioneResponse> getAllPrenotazioni() {
    List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
    return prenotazioni.stream().map(this::mapToPrenotazioneResponse).toList();
  }

  /**
   * <p>Questo metodo serve per modificare una prenotazione.Cerca una corrispondenza con
   * il codice. Se non la trova manda una eccezione. Altrimenti sovrascrive i dati del parametro
   * 'prenotazione'sui dati della corrispondenza trovata.</p>
   *
   * @param codice       utilizzato per verificare la presenza della prenotazione da modificare
   *                     all'interno del DB.
   * @param prenotazione prenotazione da modificare
   */
  @Override
  public void updatePrenotazione(String codice, Prenotazione prenotazione) {
    Prenotazione prenotazioneSalvata = prenotazioneRepository.findById(codice).orElseThrow(
        () -> new PrenotazioneNotFoundException(
            "Prenotazione con CODICE: |" + codice + "| non trovata"));
    if (prenotazione.getData() != null) {
      prenotazioneSalvata.setData(prenotazione.getData());
    }
    if (prenotazione.getSala() != null) {
      prenotazioneSalvata.setSala(prenotazione.getSala());
    }
    if (prenotazione.getPoltrona() != null) {
      prenotazioneSalvata.setPoltrona(prenotazione.getPoltrona());
    }
    if (prenotazione.getCodiceFarmaci() != null) {
      prenotazioneSalvata.setCodiceFarmaci(prenotazione.getCodiceFarmaci());
    }
    prenotazioneRepository.save(prenotazioneSalvata);
  }

  /**
   * <p>Questo metodo viene utilizzato per eliminare una prenotazione dal Database.
   * Cerca una corrispodeza tramite il codice. Se non la trova manda un eccezione. Altrimenti la
   * elimina dal Database.</p>
   *
   * @param codice codice relativo alla prenotazione da eliminare
   */
  @Override
  public void deletePrenotazione(String codice) {
    Prenotazione prenotazione = prenotazioneRepository.findById(codice).orElseThrow(
        () -> new PrenotazioneNotFoundException(
            "Prenotazione con CODICE: |" + codice + "| non trovata"));
    prenotazioneRepository.deleteById(codice);
  }

  /**
   * <p>Questo metodo restituisce una prenotazione con un determinato codice.</p>
   *
   * @param codice identificativo della prenotazione
   * @return una prenotazione
   */
  @Override
  public PrenotazioneResponse getById(String codice) {
    Prenotazione prenotazione = prenotazioneRepository.findById(codice).orElseThrow(
        () -> new PrenotazioneNotFoundException(
            "Prenotazione con CODICE: |" + codice + "| non trovata"));
    return mapToPrenotazioneResponse(prenotazione);
  }

}
