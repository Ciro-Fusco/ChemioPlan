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
 *<p>Classe controller che gestisce tutte le chiamate dal browser riferite alle prenotazioni.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@RestController
@RequestMapping("prenotazioni")
public class PrenotazioneController {

  /**
   * <p>Riferimento all'interfaccia prenotazione service.</p>
   */
  @Autowired
  private PrenotazioneService prenotazioneService;

  /**
   * <p>Questo metodo inserisce una nuova prenotazione nel database.</p>
   *
   * @param prenotazioneRequest
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void addPrenotazione(@RequestBody PrenotazioneRequest prenotazioneRequest) {
    prenotazioneService.addPrenotazione(prenotazioneRequest);
  }

  /**
   * <p>Questo metodo restituisce tutti le prenotazioni presenti nel database con una chiamata
   * GET.</p>
   *
   * @return
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<PrenotazioneResponse> getAllPrenotazioni() {
    return prenotazioneService.getAllPrenotazioni();
  }

  /**
   * <p>Questo metodo modifica una prenotazione presente nel databaase con uno specifico
   * codice.</p>
   *
   * @param codice prenotazione
   */
  @PutMapping("/{codice}")
  @ResponseStatus(HttpStatus.OK)
  public void updatePrenotazione(@PathVariable String codice,
      @RequestBody Prenotazione prenotazione) {
    prenotazioneService.updatePrenotazione(codice, prenotazione);
  }

  /**
   * <p>Questo metodo elimina la prenotazione con un determinato codice.</p>
   *
   * @param codice
   */
  @DeleteMapping("/elimina/{codice}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePrenotazione(@PathVariable String codice) {
    prenotazioneService.deletePrenotazione(codice);
  }

  /**
   * <p>Questo metodo restituisce una prenotazione con un determinato codice.</p>
   *
   * @param codice identificativo della prenotazione
   * @return una prenotazione
   */
  @GetMapping("/{codice}")
  public PrenotazioneResponse getById(@PathVariable String codice) {
    return  prenotazioneService.getById(codice);
  }


}
