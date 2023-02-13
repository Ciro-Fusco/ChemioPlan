package com.example.frontend.service;


import com.example.frontend.model.Lotto;
import com.example.frontend.model.Ordine;
import com.example.frontend.model.OrdineRequest;
import com.example.frontend.model.SchedaFarmaco;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Questa classe implementa le funzionalità di IFarmaciaService.</p>
 *
 * @version 0.1
 */
@Service
public class FarmaciaService implements InterfaceFarmaciaService {
  private RestTemplate restTemplate = new RestTemplate();
  private String farmaciaResourceUrl = "http://localhost:8080/farmacia";

  /**
   * <p>Questo metodo restituisce un faramco con un determinato Id.</p>
   *
   * @return SchedaFarmaco
   */
  @Override
  public SchedaFarmaco getFarmaco(String id) {
    SchedaFarmaco s = restTemplate.getForObject(farmaciaResourceUrl
            + "/" + id, SchedaFarmaco.class);
    return s;
  }

  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  @Override
  public SchedaFarmaco[] getAllFarmaci() {
    SchedaFarmaco[] s = restTemplate.getForObject(farmaciaResourceUrl, SchedaFarmaco[].class);
    return s;
  }

  /**
   * <p>Questo metodo restituisce tutti i farmaci disponibili nel database.</p>
   *
   * @return lista di SchedaFarmaco
   */
  @Override
  public SchedaFarmaco[] getAllFarmaciDisponibili() {
    SchedaFarmaco[] s = restTemplate.getForObject(farmaciaResourceUrl
            + "/disponibili", SchedaFarmaco[].class);
    return s;
  }

  /**
   * <p>Questo metodo inserice una nuova SchedaFarmaco nel database.</p>
   *
   * @param scheda la scheda da inserire
   */
  @Override
  public String addFarmaco(SchedaFarmaco scheda) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaFarmaco> entity = new HttpEntity<>(scheda, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(farmaciaResourceUrl, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo inserisce nella SchedaFarmaco un nuovo lotto.</p>
   *
   * @param codice codice della scheda a cui aggiungere il lotto
   * @param lotto il lotto con tutte le nuove informazioni
   *
   */
  @Override
  public String nuovoLotto(String codice, Lotto lotto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Lotto> entity = new HttpEntity<>(lotto, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(farmaciaResourceUrl
              + "/nuovo-lotto/" + codice, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo modifica una SchedaFarmaco presente nel database.</p>
   *
   * @param codice il codice della scheda da modificare
   * @param schedaFarmaco la scheda con le modifiche
   */
  @Override
  public String modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaFarmaco> entity = new HttpEntity<>(schedaFarmaco, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(farmaciaResourceUrl + "/"
              + codice, HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo fornisce il lotto cercato.</p>
   *
   * @param codiceFarmaco codice del farmaco in cui si cerca il lotto
   * @param numeroLotto lotto da ricercare
   * @return lotto cercato
   */
  @Override
  public Lotto getLotto(String codiceFarmaco, Integer numeroLotto) {
    Lotto l = restTemplate.getForObject(
            farmaciaResourceUrl + "/get-lotto/" + codiceFarmaco + "/" + numeroLotto,
            Lotto.class
    );
    return l;
  }

  /**
   * <p>Modifca il lotto scelto di un farmaco.</p>
   *
   * @param codiceFarmaco farmaco su cui modificare il lotto
   * @param lotto lotto con le modifiche
   */
  @Override
  public String modificaLotto(String codiceFarmaco, Lotto lotto) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Lotto> entity = new HttpEntity<>(lotto, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(farmaciaResourceUrl
              + "/modifica-lotto/" + codiceFarmaco, HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo inserisce un nuovo Ordine nel database.</p>
   *
   * @param ordine l'ordine da inserire
   * @return un ordine con l'id autogenerato dal database
   */
  @Override
  public String nuovoOrdine(OrdineRequest ordine) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<OrdineRequest> entity = new HttpEntity<>(ordine, headers);
    try {
      return restTemplate.postForEntity(farmaciaResourceUrl
              + "/nuovo-ordine", entity, String.class).getBody();
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo restituisce tutti gli ordini.</p>
   *
   * @return lista di ordini
   */
  @Override
  public Ordine[] getAllOrdini() {
    return restTemplate.getForObject(farmaciaResourceUrl + "/ordini", Ordine[].class);
  }

  /**
   * <p>Questo metodo elimina una SchedaFarmaco nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   */
  @Override
  public String eliminaFarmaco(String codice) {
    try {
      restTemplate.delete(farmaciaResourceUrl + "/" + codice, getFarmaco(codice));
      return "Scheda " + codice + " eliminata";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo elimina una lotto nel database.</p>
   *
   * @param codice il codice della scheda da eliminare
   * @param lotto lotto da elimiare
   */
  @Override
  public String eliminaLotto(String codice, Lotto lotto) {
    try {
      restTemplate.delete(farmaciaResourceUrl
              + "/lotto/" + codice + "/" + lotto.getNumeroLotto(), getFarmaco(codice));
      return "Lotto eliminato";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
