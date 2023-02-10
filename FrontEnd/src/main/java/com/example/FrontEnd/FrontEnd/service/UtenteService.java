package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Questa classe implementa le funzionalità di IUtenteService.</p>
 *
 * @version 0.1
 */
@Service
public class UtenteService implements IUtenteService {

  private RestTemplate restTemplate = new RestTemplate();
  private String utenteResourceUrl = "http://localhost:8080/utente";

  /**
   * <p>Questo metodo restituisce un utente con un determinato id.</p>
   *
   * @param id id dell'utente
   */
  @Override
  public Utente getUtente(Integer id) {
    Utente u = restTemplate.getForObject(utenteResourceUrl + "/" + id, Utente.class);
    return u;
  }

  /**
   * <p>Questo metodo restituisce tutti gli utenti presenti nel Database.</p>
   *
   * @return una lista di utenti
   */
  @Override
  public Utente[] getUtenti() {
    Utente[] u = restTemplate.getForObject(utenteResourceUrl, Utente[].class);
    return u;
  }

  /**
   * <p>Verifica se le credenziali nel Database coincidono con quelle inserite.</p>
   *
   * @param credenziali credenziali dell'utente
   */
  @Override
  public String verificaCrendenziali(Credenziali credenziali) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Credenziali> entity = new HttpEntity<>(credenziali, headers);
    ResponseEntity<String> response = null;

    try {
      response = restTemplate.postForEntity(utenteResourceUrl + "/login", entity, String.class);
      System.out.println(response.getBody());
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
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
    return restTemplate.postForObject(utenteResourceUrl + "/ruolo", user, String.class);
  }

  /**
   * <p>Inserisce un nuovo utente nel Database.</p>
   *
   * @param utente Utente da inserire nel Database
   */
  @Override
  public String aggiungiUtente(Utente utente) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Utente> entity = new HttpEntity<>(utente, headers);
    ResponseEntity<String> response = null;

    try {
      response = restTemplate.postForEntity(utenteResourceUrl, entity, String.class);
      System.out.println(response.getBody());
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Modifica un utente in base all'id con delle nuove informazioni.</p>
   *
   * @param id     dell'utente da modificare
   * @param utente contiene le informazioni dell'utente da aggiornare
   * @since 1.0
   */
  @Override
  public String modificaUtente(Integer id, Utente utente) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Utente> entity = new HttpEntity<>(utente, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(utenteResourceUrl
              + "/" + id, HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Elimina un utente in base all'id.</p>
   *
   * @param id dell'utente da eliminare
   */
  @Override
  public String elimina(Integer id) {
    try {
      restTemplate.delete(utenteResourceUrl + "/" + id);
      return "Utente " + id + " eliminato";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
