package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import java.util.HashMap;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PazienteService implements IPazienteService {

  private RestTemplate restTemplate = new RestTemplate();
  private String pazienteResourceUrl = "http://localhost:8080/pazienti";

  @Override
  public SchedaPaziente[] getPazienti() {
    SchedaPaziente[] pazienti = restTemplate.getForObject(pazienteResourceUrl, SchedaPaziente[].class);
    return pazienti;
  }

  @Override
  public SchedaPaziente getPaziente(String cf) {
    try {
      return restTemplate.getForObject(pazienteResourceUrl + "/" + cf, SchedaPaziente.class);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public String addPaziente(SchedaPaziente paziente) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaPaziente> entity = new HttpEntity<>(paziente, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.postForEntity(pazienteResourceUrl, entity, String.class);
      return response.getBody();
    } catch (Exception e){
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  @Override
  public String modificaPaziente(SchedaPaziente scheda) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<SchedaPaziente> entity = new HttpEntity<>(scheda, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(pazienteResourceUrl + "/" + scheda.getCodiceFiscale(), HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e){
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  @Override
  public String eliminaPaziente(String cf) {
    try {
      restTemplate.delete(pazienteResourceUrl + "/" + cf);
      return "SchedaPaziente " + cf + " eliminata";
    } catch (Exception e){
      return e.getMessage();
    }
  }

  @Override
  public HashMap<String, Double> getFarmaci(String cf) {
    HashMap<String,Double> farmaci = restTemplate.getForObject(pazienteResourceUrl +"/getFarmaci/" + cf, HashMap.class);
    return farmaci;
  }
}
