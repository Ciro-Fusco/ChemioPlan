package com.example.FrontEnd.FrontEnd.service;


import com.example.FrontEnd.FrontEnd.model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
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
  @Autowired
  private IFarmaciaService farmaciaService;

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
      return restTemplate.getForObject(prenotazioneResourceUrl + "/" + codice, Prenotazione.class);
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public Prenotazione[] getByData(Date data) {
    try {
      return restTemplate.getForObject(prenotazioneResourceUrl + "/getByData/" + data, Prenotazione[].class);
    } catch (Exception e) {
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
    } catch (Exception e) {
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
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  @Override
  public String deletePrenotazione(String codice) {
    try {
      restTemplate.delete(prenotazioneResourceUrl + "/elimina/" + codice, getById(codice));
      return "Prenotazione " + codice + " eliminata";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @Override
  public boolean confermaPrenotazione(SchedaPaziente p) {
    HashMap<String, Double> farmaci = p.getFarmaci();

    Set<String> keySet = farmaci.keySet();
    for (String key : keySet) {
      double dosaggio = farmaci.get(key);
      SchedaFarmaco s = farmaciaService.getFarmaco(key);
      List<Lotto> lotti = s.getLotti();

      //verifica della disponibilità del farmaco
      int quantitaDisponibile = 0;
      for (Lotto l: lotti) {
        quantitaDisponibile += l.getQuantita();
      }

      System.out.println(quantitaDisponibile + " | " + dosaggio);
      if (quantitaDisponibile < dosaggio) {
        return false;
      }
      for (Lotto lotto : lotti) {
        if (lotto.getQuantita() <= dosaggio) {
          dosaggio -= lotto.getQuantita();
          lotto.setQuantita(0);
          farmaciaService.modificaLotto(key, lotto);
        } else if (lotto.getQuantita() > dosaggio) {
          lotto.setQuantita(lotto.getQuantita() - (int) dosaggio);
          farmaciaService.modificaLotto(key, lotto);
        }
      }
    }
    return true;
  }
}
