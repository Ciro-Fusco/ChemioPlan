package com.example.FrontEnd.FrontEnd.service;


import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * <p>Questa classe </p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (26-01-2023)
 */
@Service
public class PrenotazioneService implements IPrenotazioneService {

  private RestTemplate restTemplate = new RestTemplate();

  private String prenotazioneResourceUrl = "http://localhost:8083/prenotazioni";

  /**
   * <p>Questo .</p>
   *
   * @return retistuisce una lista di PrenotazioniResponse
   */
  @Override
  public Prenotazione[] getAllPrenotazioni() {
    Prenotazione[] p = restTemplate.getForObject(prenotazioneResourceUrl, Prenotazione[].class);
    return p;
  }

  /**
   * <p>Questo metodo restituisce una prenotazione con un determinato codice.</p>
   *
   * @param codice identificativo della prenotazione
   * @return una prenotazione
   */
  @Override
  public Prenotazione getById(String codice) {
    try {
      return  restTemplate.getForObject(prenotazioneResourceUrl + "/" + codice, Prenotazione.class);
    }catch (Exception e) {
      return null;
    }
  }

  @Override
  public String addPrenotazione(Prenotazione prenotazione) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Prenotazione> entity = new HttpEntity<>(prenotazione, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(prenotazioneResourceUrl, entity, String.class);
      return response.getBody();
    } catch (Exception e){
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  @Override
  public String updatePrenotazione(Prenotazione prenotazione) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Prenotazione> entity = new HttpEntity<>(prenotazione, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(prenotazioneResourceUrl + "/" + prenotazione.getCodice(), HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e){
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  @Override
  public String deletePrenotazione(String codice) {
    try {
      restTemplate.delete(prenotazioneResourceUrl + "/elimina/" + codice, getById(codice));
      return "Prenotazione " + codice + " eliminata";
    } catch (Exception e){
      return e.getMessage();
    }
  }

}
