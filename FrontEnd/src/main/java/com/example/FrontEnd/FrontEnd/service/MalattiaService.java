package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Malattia;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MalattiaService implements IMalattiaStub{

  private RestTemplate restTemplate = new RestTemplate();
  private String url = "http://localhost:8080/fhir/malattia";

  @Override
  public Malattia getMalattia(String codice) {
    return restTemplate.getForObject(url + "/codice/" + codice, Malattia.class);
  }

  @Override
  public Malattia[] getAll() {
    return restTemplate.getForObject(url, Malattia[].class);
  }
}
