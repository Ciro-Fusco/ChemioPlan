package com.example.FrontEnd.FrontEnd.service;



import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.Prenotazione;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.SchedaPaziente;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * <p>Questa classe implementa le funzionalità di IPrenotazioneService.</p>
 *
 * @version 0.1
 */
@Service
public class PrenotazioneService implements IPrenotazioneService {
  @Autowired
  private IFarmaciaService farmaciaService;

  private RestTemplate restTemplate = new RestTemplate();

  private String prenotazioneResourceUrl = "http://localhost:8083/prenotazioni";

  /**
   * <p>Questo metodo viene utilizzato per ottenere tutte le prenotazioni presenti nel Database.</p>
   *
   * @return retistuisce una lista di Prenotazioni
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

  /**
   * <p>Questo metodo restituisce una lista di prenotazioni con una determinata data.</p>
   *
   * @param data data delle prenotazioni
   * @return lista di prenotazione
   */
  @Override
  public Prenotazione[] getByData(Date data) {
    try {
      return restTemplate.getForObject(prenotazioneResourceUrl
              + "/getByData/" + data, Prenotazione[].class);
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * <p>Questo metodo viene utilizzato per inserire una nuova prenotazione nel Database.</p>
   *
   * @param prenotazione oggetto che rappresenta una prenotazione
   */
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

  /**
   * <p>Questo metodo serve per modificare una prenotazione</p>.
   *
   * @param prenotazione prenotazione da modificare
   */
  @Override
  public String updatePrenotazione(Prenotazione prenotazione) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Prenotazione> entity = new HttpEntity<>(prenotazione, headers);
    ResponseEntity<String> response = null;
    try {
      response = restTemplate.exchange(prenotazioneResourceUrl
              + "/" + prenotazione.getCodice(), HttpMethod.PUT, entity, String.class);
      return response.getBody();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo viene utilizzato per eliminare una prenotazione dal Database.
   * Cerca una corrispodeza tramite il codice. Se non la trova manda un eccezione. Altrimenti la
   * elimina dal Database.</p>
   *
   * @param codice codice relativo alla prenotazione da eliminare
   */
  @Override
  public String deletePrenotazione(String codice) {
    try {
      restTemplate.delete(prenotazioneResourceUrl + "/elimina/" + codice, getById(codice));
      return "Prenotazione " + codice + " eliminata";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  /**
   * <p>Questo metodo va a ridurre la quantità del farmaco destinato a quel paziente
   * dalla quantità di farmaco disponibile e conferma la prenotazione.</p>
   *
   * @param p scheda paziente
   */
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
      for (Lotto l : lotti) {
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
