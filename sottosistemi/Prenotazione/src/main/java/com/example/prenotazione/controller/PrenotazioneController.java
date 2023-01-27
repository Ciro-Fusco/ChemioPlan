package com.example.prenotazione.controller;

import com.example.prenotazione.dto.PrenotazioneRequest;
import com.example.prenotazione.dto.PrenotazioneResponse;
import com.example.prenotazione.model.Prenotazione;
import com.example.prenotazione.service.PrenotazioneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>Questa classe è responsabile dell'elaborazione delle richieste API REST in entrata,
 * della preparazione di un modello e della restituzione della vista da rendere come risposta.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@RestController
@RequestMapping("prenotazioni")
public class PrenotazioneController {

  /**
   * <p>Riferimento al layer Service.</p>
   */
  @Autowired
  private PrenotazioneService prenotazioneService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addPrenotazione(@RequestBody PrenotazioneRequest prenotazioneRequest) {
    prenotazioneService.addPrenotazione(prenotazioneRequest);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PrenotazioneResponse> getAllPrenotazioni() {
    return prenotazioneService.getAllPrenotazioni();
  }

  @PutMapping("/{codice}")
  @ResponseStatus(HttpStatus.OK)
  public void updatePrenotazione(@PathVariable String codice,
      @RequestBody Prenotazione prenotazione) {
    prenotazioneService.updatePrenotazione(codice, prenotazione);
  }

  @DeleteMapping("/elimina/{codice}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePrenotazione(@PathVariable String codice) {
    prenotazioneService.deletePrenotazione(codice);
  }

  @GetMapping("/{codice}")
  public PrenotazioneResponse getById(@PathVariable String codice) {
    return  prenotazioneService.getById(codice);
  }


}
