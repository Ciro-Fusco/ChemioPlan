package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import java.util.HashMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Questa classe implementa le funzionalità di IPazienteService.</p>
 *
 * @version 0.1
 */
@Service
public class PazienteService implements IPazienteService {

  private RestTemplate restTemplate = new RestTemplate();
  private String pazienteResourceUrl = "http://localhost:8080/pazienti";

  /**
   * <p> Questo metodo restituisce tutti i Pazienti presenti nel DB.</p>
   */
  @Override
  public SchedaPaziente[] getPazienti() {
    SchedaPaziente[] pazienti = restTemplate.getForObject(pazienteResourceUrl,
            SchedaPaziente[].class);
    return pazienti;
  }

  /**
   * <p> Questo metodo restituisce un Paziente con un determinato codiceFiscale.</p>
   *
   * @param cf il CodiceFiscale della schedaPaziente da cercare
   * @return la schedaPaziente trovata
   */
  @Override
  public SchedaPaziente getPaziente(String cf) {
    try {
      return restTemplate.getForObject(pazienteResourceUrl + "/" + cf, SchedaPaziente.class);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * <p> Questo metodo inserisce un nuovo Paziente nel DB.</p>
   *
   * @param paziente la schedaPaziente da inserire
   */
  @Override
  public String addPaziente(SchedaPaziente paziente) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaPaziente> entity = new HttpEntity<>(paziente, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(pazienteResourceUrl, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo modifica un Paziente presente nel DB.</p>
   *
   * @param scheda la schedaPaziente con le modifiche
   */
  @Override
  public String modificaPaziente(SchedaPaziente scheda) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaPaziente> entity = new HttpEntity<>(scheda, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(pazienteResourceUrl
              + "/" + scheda.getCodiceFiscale(), HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p> Questo metodo elimina Paziente presente nel DB.</p>
   *
   * @param cf il codiceFiscale della schedaPaziente da eliminare
   */
  @Override
  public String eliminaPaziente(String cf) {
    try {
      restTemplate.delete(pazienteResourceUrl + "/" + cf);
      return "SchedaPaziente " + cf + " eliminata";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * <p> Questo metodo restituisce i farmaci con il
   * relativo dosaggio di un Paziente con un determinato codiceFiscale.</p>
   *
   * @param cf il CodiceFiscale della schedaPaziente da cercare
   */
  @Override
  public HashMap<String, Double> getFarmaci(String cf) {
    SchedaPaziente paziente = restTemplate.getForObject(pazienteResourceUrl
            + "/" + cf, SchedaPaziente.class);
    return paziente.getFarmaci();
  }

  /**
   * <p> Questo metodo restituisce un Paziente in base ad un ricerca
   * con dei determinati filtri.</p>
   *
   * @param filtri il CodiceFiscale della schedaPaziente da cercare
   */
  @Override
  public SchedaPaziente[] getPazientiByFiltri(SchedaPaziente filtri) {
    return restTemplate.postForObject(pazienteResourceUrl
            + "/byPaziente", filtri, SchedaPaziente[].class);
  }
}
