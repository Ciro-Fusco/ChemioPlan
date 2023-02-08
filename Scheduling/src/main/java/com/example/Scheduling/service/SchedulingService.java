package com.example.Scheduling.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Questa classe fa lo scheduling delle seduete e ne restituisce il risultato.
 * </p>
 *
 * @author Francesco Matteis
 * @version 1.0
 */
@Service
public class SchedulingService implements IschedulingService {
  /**
  * <p>
  * Questo metodo restituisce giorni e orari consigliati consigliati per le somministrazioni.
  * </p>
  *
  * @param cf codice fiscale del paziente
  * @return giorni e orari consigliati
  */
  @Override
  public String schedula(String cf) {

    String output = "";

    try {

      String command = "python Scheduler.py " + cf;
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
