package com.example.utente.service;

import com.example.utente.exception.CredenzialiNonValideException;
import com.example.utente.exception.UtenteAlreadyExistException;
import com.example.utente.exception.UtenteNotFoundException;
import com.example.utente.model.Utente;
import com.example.utente.repository.UtenteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


/**
 * <p>Classe che implementa i metodi dell'interfaccia IUtenteService.</p>
 *
 * @author chris
 * @version 1.0
 */
@Service
public class UtenteService implements IutenteService {
  /**
   * <p>Riferimento alla classe IUtenteService.</p>
  */
  @Autowired
  private UtenteRepository repository;

  /**
   * <p>Questo metodo restituisce tutti gli utenti presenti nel Database.</p>
   *
   * @return una lista di utenti
   * @since 1.0
  */
  @Override
  public List<Utente> getAllUtenti() {
    return repository.findAll();
  }

  /**
   * <p>Questo metodo restituisce un utente in base all'id.</p>
   *
   * @param id dell'utente da cercare
   * @return un utente con un id specifico
   * @since 1.0
   */
  @Override
  public Utente getUtenteById(Integer id) {
    return repository.findById(id).orElseThrow(
            () -> new UtenteNotFoundException("Utente con codice: " + id + "non trovato"));
  }

  /**
   * <p>Elimina un utente in base all'id.</p>
   *
   * @param id dell'utente da eliminare
   * @since 1.0
   */
  @Override
  public void deleteUtente(Integer id) {
    var obj = repository.findById(id);
    if (obj.isEmpty()) {
      new UtenteNotFoundException("Utente da rimuovere con codice " + id + " non trovato");
    }
    repository.deleteById(id);
  }

  /**
   * <p>Modifica un utente in base all'id con delle nuove informazioni.</p>
   *
   * @param id dell'utente da modificare
   * @param u contiene le informazioni dell'utente da aggiornare
   * @since 1.0
   */
  @Override
  public void updateUtente(Integer id, Utente u) {
    if (repository.findById(id).isEmpty()) {
      throw new UtenteNotFoundException("Utente da modificare con codice " + id + " non trovato");
    }
    repository.save(u);
  }

  /**
   * <p>Inserisce un nuovo utente nel Database.</p>
   *
   * @param u Utente da inserire nel Database
   * @since 1.0
   */
  @Override
  public void addUtente(Utente u) {
    if (repository.existsById(u.getId())) {
      throw new UtenteAlreadyExistException("Utente con codice " + u.getId() + " già esistente");
    }
    if (repository.findByUser(u.getCredenziali().getUser()) != null) {
      throw new CredenzialiNonValideException("Username esistente");
    }
    repository.save(u);
  }

  /**
   * <p>Verifica se le credenziali nel Database coincidono.</p>
   *
   * @param user Username dell'utente
   * @param pass Password dell'utente
   * @since 1.0
     */
  @Override
  public ResponseEntity<?> verificaCredenziali(String user, String pass) {
    Utente u = repository.findByUser(user);
    if (u == null) {
      throw new CredenzialiNonValideException("User Errato");
    }

    if (BCrypt.checkpw(pass, u.getCredenziali().getPass())) {
      System.out.println("Login effettuato con successo");
      return ResponseEntity.ok("Login effettuato con successo!");
    } else {
      System.out.println("Le credenziali sono errate");
      throw new CredenzialiNonValideException("Credenziali non valide");
    }
  }

  /**
   * <p>Ricerca il ruolo dell'utente in base allo username.</p>
   *
   * @param user username dell'utente
   * @return ruolo dell'utente
   */
  @Override
  public String getRuoloByUser(String user) {
    return repository.findByUser(user).getRuolo();
  }
}
