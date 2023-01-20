package com.example.utente.controller;


import com.example.utente.model.Credenziali;
import com.example.utente.model.Utente;
import com.example.utente.service.IutenteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>La classe UtenteController è responsabile per le richieste in entrata.</p>
 *
 * @author chris
 * @version 1.0
 */
@RestController
@RequestMapping("utente")
public class UtenteController {
  /**
   * <p>Riferimento alla classe IUtenteService.</p>
  */
  @Autowired
  private IutenteService service;

  /**
   * <p>Questo metodo restituisce la lista di tutti gli utenti all'interno del Database.</p>
   *
   * @return la lista degli utenti all'interno del Database
   * @since 1.0
  */
  @GetMapping
  public List<Utente> ottieniUtenti() {
    return service.getAllUtenti();
  }

  /**
   * <p>Questo metodo restituisce un determinato utente in base all'id.</p>
   *
   * @param id : id dell'utente da cercare nel
   * @return Utente con l'id in input
   * @since 1.0
  */
  @GetMapping("/{id}")
  public Utente ottieneUtentePerId(@PathVariable Integer id) {
    return service.getUtenteById(id);
  }

  /**
   * <p>Questo metodo inserisce l'utente nel Database.</p>
   *
   * @param u Utente che viene passato nel body
   * @return un ResponseEntity con un messaggio
   * @since 1.0
  */
  @PostMapping
  public ResponseEntity<?> aggiungiUtente(@RequestBody Utente u) {
    service.addUtente(u);
    return ResponseEntity.ok("Utente salvato con codice : " + u.getId());
  }

  /**
   * <p>Questo metodo rimuove l'utente con l'id dal Database.</p>
   *
   * @param id id dell'utente che deve essere rimosso
   * @return un RespondeEntity con un messaggio
   * @since 1.0
  */
  @DeleteMapping("/{id}")
  public ResponseEntity<?> rimuoviUtente(@PathVariable Integer id) {
    service.deleteUtente(id);
    return ResponseEntity.ok("Utente rimosso correttamente con codice : " + id);
  }

  /**
   * <p>Questo metodo modifica l'utente nel Database con uno specifico codice.</p>
   *
   * @param id id dell'utente che viene passato nell'url
   * @param u dati dell'utente che devono essere aggiornati
   * @return un RespondeEntity con un messaggio
   * @since 1.0
   */
  @PutMapping("/{id}")
  public ResponseEntity<?> modificaUtente(@PathVariable Integer id, @RequestBody Utente u) {
    service.updateUtente(id, u);
    return ResponseEntity.ok("Utente con codice : " + id + " modificato correttamente");
  }

  /**
   * <p>Effettua il login in base alle credenziali inserite.</p>
   *
   * @param credenziali credenziali per effettuare il login
   * @return un RespondeEntity con un messaggio
   * @since 1.0
   */
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Credenziali credenziali) {
    service.verificaCredenziali(credenziali.getUser(), credenziali.getPass());
    return ResponseEntity.ok("Login effettuato con succeso!");
  }
}
