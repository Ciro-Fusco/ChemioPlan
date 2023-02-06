package com.chemioplan.SchedaPaziente.controller;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import com.chemioplan.SchedaPaziente.service.SchedaPazienteService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>Questa classe SchedaPazienteController è responsabile
 * dell'elaborazione delle richieste in entrata.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */
@RestController
@RequestMapping("pazienti")
public class SchedaPazienteController {
  /**
   * <p> riferimento alla classe service.</p>
   */
  @Autowired
  private SchedaPazienteService schedaPazienteService;

  /**
   * <p> Questo metodo restituisce tutte le SchedePazienti
   * presenti nel DB con una chiamata GET.</p>
   *
   * @return la lista di tutti i pazienti presenti nel Database
   */
  @GetMapping
  public List<SchedaPaziente> ottieniSchedePazienti() {
    return schedaPazienteService.ottieniSchedePazienti();
  }

  /**
   * <p>Questo metodo , con chiamata Get, restituisce una determinata scheda paziente
   * con un codiceFiscale presente nell'url.</p>
   *
   * @param codiceFiscale il codice fiscale del paziente
   * @Return la schedaPaziente con il codiceFiscale dato in input
   */
  @GetMapping("/ {codiceFiscale}")
  public SchedaPaziente ottieniSchedaPaziente(@PathVariable String codiceFiscale) {
    return schedaPazienteService.ottieniSchedaPazientePerCodiceFiscale(codiceFiscale);
  }

  /**
   * <p>Questo metodo inserisce una nuova SchedaPaziente nel DB.</p>
   *
   * @param schedaPaziente la scheda che viene passata nel body della richiesta
   * @return una ResponseEntity con un messaggio e lo status HTTP
   */
  @PostMapping
  public ResponseEntity<?> aggiungiSchedaPaziente(@RequestBody SchedaPaziente schedaPaziente) {
    schedaPazienteService.aggiungiSchedaPaziente(schedaPaziente);
    return new ResponseEntity<String>("SchedaPaziente salvata "
            + schedaPaziente.getCodiceFiscale(), HttpStatus.CREATED);
  }

  /**
   * <p>Questo metodo modifica una schedaPaziente presente nel DB.</p>
   *
   * @param codiceFiscale  il codiceFiscale della schedaPaziente passato nell'url
   * @param schedaPaziente la schedaPaziente con le modifiche passata nel body della richiesta
   * @return una ResponseEntity con un messaggio e lo status HTTP
   */
  @PutMapping("/{codiceFiscale}")
  public ResponseEntity<?> modificaSchedaPaziente(@PathVariable String codiceFiscale,
                                                  @RequestBody SchedaPaziente schedaPaziente) {
    schedaPazienteService.modificaSchedaPaziente(codiceFiscale, schedaPaziente);
    return ResponseEntity.ok("SchedaPaziente modificata correttamente"
            + schedaPaziente.getCodiceFiscale());
  }

  /**
   * <p>Questo metodo elimina la schedaPaziente con un determinato codiceFiscale.</p>
   *
   * @param codiceFiscale il codiceFiscale della schedaPaziente da eliminare
   * @return una ResponseEntity con un messaggio
   */
  @DeleteMapping("/{codiceFiscale}")
  public ResponseEntity<?> eliminaSchedaPaziente(@PathVariable String codiceFiscale) {
    schedaPazienteService.eliminaSchedaPaziente(codiceFiscale);
    return ResponseEntity.ok("Scheda farmaco eliminata correttamente" + codiceFiscale);
  }

  /**
   * <p>Questo metodo , con chiamata Get, restituisce i farmaci di un paziente relativo
   * ad un codiceFiscale presente nell'url.</p>
   *
   * @param codiceFiscale il codice fiscale del paziente
   * @Return la schedaPaziente con il codiceFiscale dato in input
   */
  @GetMapping("/getFarmaci/{codiceFiscale}")
  public HashMap<String, Double> ottieniFarmaciPaziente(@PathVariable String codiceFiscale) {
    return schedaPazienteService.ottieniFarmaciPerCodiceFiscale(codiceFiscale);
  }
}
