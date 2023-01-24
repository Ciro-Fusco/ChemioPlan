package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Paziente_Service implements IPaziente_Service {

  private RestTemplate restTemplate = new RestTemplate();
  private String pazienteResourceUrl = "http://localhost:8080/pazienti";

  @Override
  public SchedaPaziente[] getPazienti() {
    SchedaPaziente[] pazienti = restTemplate.getForObject(pazienteResourceUrl, SchedaPaziente[].class);
    return pazienti;
  }

  @Override
  public SchedaPaziente getPazienteByCF(String cf) {
    SchedaPaziente paziente = restTemplate.getForObject(pazienteResourceUrl + "/by-cf/" + cf, SchedaPaziente.class);
    return paziente;
  }
}
