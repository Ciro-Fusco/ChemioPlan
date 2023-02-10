package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Paziente;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * <p>Questa classe implementa le funzionalità di IPazienteStub.</p>
 */
@Service
public class PazienteStub implements IPazienteStub {
  private RestTemplate restTemplate = new RestTemplate();

  private String url = "http://localhost:8080/fhir/paziente";

  /**
   * <p>Questo metodo cerca un paziente in base al codice fiscale.</p>
   *
   * @param cf il codice fiscale del paziente
   * @return il paziente con quel determinato codice fiscale
   */
  @Override
  public Paziente findByCf(String cf) {
    return restTemplate.getForObject(url + "/" + cf, Paziente.class);
  }

  /**
   * <p>Questo metodo restituisce una lista di paziente in base al filtro di ricerca inserito.</p>
   *
   * @param p i dati del paziente inseriti
   * @return una lista di pazienti
   */
  @Override
  public List<Paziente> findPazienti(Paziente p) {
    return restTemplate.postForObject(url + "/trova-paziente", p, List.class);
  }

  /**
   * <p>Questo metodo restituisce una lista di tutti i pazienti.</p>
   *
   * @return una lista di tutti i pazienti
   */
  @Override
  public List<Paziente> findAllPazienti() {
    return restTemplate.getForObject(url, List.class);
  }

}
