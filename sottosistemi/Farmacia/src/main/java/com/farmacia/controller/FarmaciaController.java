package com.farmacia.controller;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.model.Lotto;
import com.farmacia.model.Ordine;
import com.farmacia.model.SchedaFarmaco;
import com.farmacia.service.FarmaciaService;
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
 * <p>La classe Farmacia controller è responsabile dell'elaborazione delle richieste in entrata.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1 (11-01-2023)
 */
@RestController
@RequestMapping("farmacia")
public class FarmaciaController {
  /**
   * <p>Riferimento alla classe service.</p>
   */
  @Autowired
  private FarmaciaService service;

  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database con una chiamata GET.</p>
   *
   * @return la lista di tutti i farmaci presenti nel Database
   */
  @GetMapping
  public List<SchedaFarmaco> ottieniFarmaci() {
    return service.ottieniFarmaci();
  }

  /**
   * <p>Questo metodo, con chiamata GET,
   * restituisce un determinato farmaco con un codice, presente nell'url.</p>
   *
   * @param codice il codice del farmaco presente nell'url
   * @return il farmaco con il codice di input
   */
  @GetMapping("/{codice}")
  public SchedaFarmaco ottieniFarmaco(@PathVariable String codice) {
    return service.ottieniFarmacoPerCodice(codice);
  }

  /**
   * <p>Questo metodo inserisce una nuova scheda farmaco nel database.</p>
   *
   *
   * @param schedaFarmaco la scheda che viene passata nel body della richiesta
   * @return un ResponseEntity con un messaggio e lo status HTTP
   */
  @PostMapping
  public ResponseEntity<?> aggiungiFarmaco(@RequestBody SchedaFarmaco schedaFarmaco) {
    service.aggiungiFarmaco(schedaFarmaco);
    return new ResponseEntity<String>("Scheda Farmaco salvata " + schedaFarmaco.getCodice(), HttpStatus.CREATED);
  }

  /**
   * <p>Questo metodo modifica una scheda farmaco presente nel databaase con uno specifico codice.</p>
   *
   * @param codice il codice della scheda farmaco passato nel'url
   * @param schedaFarmaco la scheda farmaco con le modifiche passata nel body della richiesta
   * @return un ResponseEntity con un messaggio e lo status HTTP
   */
  @PutMapping("/{codice}")
  public ResponseEntity<?> modificaFarmaco(@PathVariable String codice, @RequestBody SchedaFarmaco schedaFarmaco) {
    service.modificaFarmaco(codice, schedaFarmaco);
    return ResponseEntity.ok("Scheda Farmaco modificata correttamente" + schedaFarmaco.getCodice());
  }

  /**
   * <p>Questo metodo elimina la scheda farmaco con un determinato codice.</p>
   *
   * @param codice il codice della scheda farmaco da eliminare
   * @return un ResponseEntity con un messaggio
   */
  @DeleteMapping("/{codice}")
  public ResponseEntity<?> eliminaFarmaco(@PathVariable String codice) {
    service.eliminaFarmaco(codice);
    return ResponseEntity.ok("Scheda Farmaco modificata correttamente" + codice);
  }

  /**
   * <p>Questo metodo modifica il lotto di un farmaco.</p>
   *
   * @param lotto il lotto che contiene le informazioni da modificare
   * @return un ResponseEntity con un messaggio
   */
  @PostMapping("/nuovo-lotto")
  public ResponseEntity<?> nuovoLotto(@RequestBody Lotto lotto) {
    service.nuovoLotto(lotto);
    return ResponseEntity.ok("Nuovo lotto modificato correttamente sulla Scheda Farmaco " + lotto.getCodiceFarmaco());
  }

  /**
   * <p>Questo metodo inserisce un nuovo ordine nel database.</p>
   *
   * @param ordine l'ordine che viene inserito nel database
   * @return un ResponseEntity con un messaggio e lo status HTTP
   */
  @PostMapping("/nuovo-ordine")
  public ResponseEntity<?> nuovoOrdine(@RequestBody OrdineRequest ordine) {
    Ordine o = service.nuovoOrdine(ordine);
    return new ResponseEntity("Nuovo ordine effettuato correttamente ID " + o.getId(), HttpStatus.CREATED);
  }

  /**
   * <p>Questo metodo restituisce l'ordine con uno specifico id.</p>
   *
   * @param id l'id del farmaco da cercare passato nell'url
   * @return l'ordine con l'id specificato
   */
  @GetMapping("/ordine")
  public Ordine ottieniOrdine(@RequestParam String id) {
    return service.ottieniOrdine(id);
  }

  /**
   * <p>Questo metodo modifica lo stato dell'ordine in Consegnato.</p>
   *
   * @param id l'id dell'ordine da modificare
   * @return un ResponseEntity con un messaggio
   */
  @GetMapping("/ordine-consegnato")
  public ResponseEntity<?> ordineConsegnato(@RequestParam String id) {
    service.ordineConsegnato(id);
    return ResponseEntity.ok("Ordine " + id + " consegnato");
  }
}
