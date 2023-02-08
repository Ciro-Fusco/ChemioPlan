package com.example.Scheduling.control;

import com.example.Scheduling.service.IschedulingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * La classe schedulingController responsabile dell'elaborazione delle
 * richieste di scheduling delle sedute di somministrazione.
 * </p>
 *
 * @author Francesco Matteis
 * @version 1.0 (08/02/2023)
 */
@RestController
@RequestMapping("scheduling")
public class SchedulingController {
  /**
   * <p>
   * Riferimento alla classe service.
   * </p>
   */
  @Autowired
  private IschedulingService service;

  /**
 * <p>
 * Questo metodo restituisce giorni e orari consigliati consigliati per le somministrazioni.
 * </p>
 *
 * @param cf codice fiscale del paziente
 * @return giorni e orari consigliati
 */
  @GetMapping()
  public String scheduling(@RequestParam("cf") String cf) {
    return service.schedula(cf);

  }
}
