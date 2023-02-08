package com.example.OttimizzazioneAcquisto.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;


/**
 * <p>
 * Questa classe fa una predizione delle numero di flaconi da acquistare e ne
 * restituisce il risultato.
 * </p>
 *
 * @author Francesco Matteis
 * @version 1.0
 */
@Service
public class OttimizzazioneService implements IottimizzazioneService {

  /**
  * <p>
  * Questo metodo restituisce il numero ottimale di flaconi da acquistare.
  * </p>
  *
  * @param codice codice del farmaco
  * @return numero di flaconi da acquistare
  */
  @Override
  public String ottimizza(String codice) {

    String output = "";

    try {

      String command = "python Modello_Deploy.py " + codice;
      System.out.println(command);
      Process process = Runtime.getRuntime().exec(command);

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String line;
      while ((line = reader.readLine()) != null) {
        System.out.println(line);
        output = output + line;
      }
      reader.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
    return output;
  }

}
