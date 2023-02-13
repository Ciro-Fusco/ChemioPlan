package com.example.frontend.service;

import com.example.frontend.model.Malattia;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Questa classe implementa le funzionalità di IMalattiaStub.</p>
 *
 * @version 0.1
 */
@Service
public class MalattiaService implements InterfaceMalattiaStub {

  private RestTemplate restTemplate = new RestTemplate();
  private String url = "http://localhost:8080/fhir/malattia";

  /**
   * <p>Questo metodo restituisce una malattia con un determinato codice.</p>
   *
   * @param codice codice della malattia
   */
  @Override
  public Malattia getMalattia(String codice) {
    return restTemplate.getForObject(url + "/codice/" + codice, Malattia.class);
  }

  /**
   * <p>Questo metodo restituisce una lista di malattie.</p>
   */
  @Override
  public Malattia[] getAll() {
    return restTemplate.getForObject(url, Malattia[].class);
  }
}
