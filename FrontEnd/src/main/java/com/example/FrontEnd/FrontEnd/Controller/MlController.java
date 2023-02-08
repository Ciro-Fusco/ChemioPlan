package com.example.FrontEnd.FrontEnd.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 * Classe controller che gestisce tutte le chiamate ajax dal browser per le
 * richieste di ottimizzazione con modelli di ML.
 * </p>
 *
 * @version 1.0
 */
@RestController
@RequestMapping(value = { "/mlservices" })
public class MlController {
  /**
   * <p>
   * Metodo che restituisce la predizione di flaconi da acquistare.
   * </p>
   *
   * @param codice codice del farmaco da acquistare
   * @return stima numero di flaconi
   */
  @GetMapping(value = { "/ottimizzazioneacquisto" })
  public String ottimizzazioneacquisto(@RequestParam("codice") String codice) {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/ottimizzazioneacquisti?codice=" + codice;
    String result = restTemplate.getForObject(url, String.class);
    if (result != null) {
      result = "Stima di acquisto: " + result + " flaconi";
    } else {
      result = "";
    }
    System.out.println(result);
    return result;

  }

  /**
   * <p>
   * Metodo che restituisce gli orari consigliati dallo scheduler.
   * </p>
   *
   * @param cf codice fiscale del paziente
   * @return giorni e orari consigliati
   */
  @GetMapping(value = { "/scheduling" })
  public String scheduling(@RequestParam("cf") String cf) {

    RestTemplate restTemplate = new RestTemplate();
    String url = "http://localhost:8080/scheduling?cf=" + cf;
    String result = restTemplate.getForObject(url, String.class);
    if (result == null) {
      result = "";
    }
    System.out.println(result);
    return result;

  }

}
