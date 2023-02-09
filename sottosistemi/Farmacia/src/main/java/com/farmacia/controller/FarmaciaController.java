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
   * <p>Questo metodo restituisce tutti i farmaci disponibili, con quantità di alemeno un lotto maggiore 0.</p>
   *
   * @return lista di farmaci
   */
  @GetMapping("/disponibili")
  public List<SchedaFarmaco> ottieniFarmaciDisponibili() {
    return service.ottieniFarmaciDisponibili();
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
   * <p>Questo metodo inserisce un nuovo lotto nel farmaco.</p>
   *
   * @param codice codice della scheda a cui aggiungere il lotto
   * @param lotto il lotto che contiene le informazioni da modificare
   * @return un ResponseEntity con un messaggio
   */
  @PostMapping("/nuovo-lotto/{codice}")
  public ResponseEntity<?> nuovoLotto(@PathVariable String codice, @RequestBody Lotto lotto) {
    service.nuovoLotto(codice, lotto);
    return ResponseEntity.ok("Nuovo lotto modificato correttamente sulla Scheda Farmaco " + codice);
  }

  /**
   * <p>Questo metodo fornisce il lotto sulla base del farmaco.</p>
   *
   * @param codice codice del farmaco da cercare
   * @param num numero del lotto
   * @return lotto del farmaco
   */
  @GetMapping("/get-lotto/{codice}/{num}")
  public Lotto ottieniLotto(@PathVariable String codice, @PathVariable Integer num) {
    return service.ottieniLotto(codice, num);
  }

  /**
   * <p>Questo metodo modifica il lotto del farmaco.</p>
   *
   * @param codice codice del farmaco
   * @param lotto lotto con le modifiche
   * @return un ResponseEntity con un messaggio e lo status HTTP
   */
  @PutMapping("/modifica-lotto/{codice}")
  public ResponseEntity<?> modificaLotto(@PathVariable String codice, @RequestBody Lotto lotto) {
    service.modificaLotto(codice, lotto.getNumeroLotto(), lotto);
    return ResponseEntity.ok("Lotto scheda farmaco modificata correttamente" + codice);
  }

  /**
   * <p>Questo metodo elimina un lotto da una scheda farmaco.</p>
   *
   * @param codice codice del farmaco
   * @param num numero del lotto da eliminare
   */
  @DeleteMapping("/lotto/{codice}/{num}")
  public ResponseEntity<?> eliminaLotto(@PathVariable String codice, @PathVariable Integer num) {
    service.eliminaLotto(codice, service.ottieniLotto(codice, num));
    return ResponseEntity.ok("Lotto eliminato");
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
   * <p>Questo metodo restituisce tutti gli ordini.</p>
   *
   * @return lista di ordini
   */
  @GetMapping("/ordini")
  public List<Ordine> ottieniOrdini() {
    return service.ottieniOrdini();
  }

  /**
   * <p>Questo metodo fornisce l'ordine selezionato.</p>
   *
   * @param id id dell'ordine
   * @return ordine presente nel DB
   */
  @GetMapping("/ordine/{id}")
  public Ordine ottieniOrdini(@PathVariable String id) {
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
