package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.MagazzinoFarmaco;
import com.example.apigateway.stub.model.Farmaco;
import java.text.ParseException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>La classe FarmacoController è responsabile dell'elaborazione delle richieste in entrata.</p>
 */
@RestController
@RequestMapping("/fhir/farmaci")
public class FarmacoController {
  /**
   * <p>Riferimento alla classe MagazzinoFarmaco.</p>
   */
  private MagazzinoFarmaco farmaci = new MagazzinoFarmaco();

  /**
   * <p>Questo metodo restituisce tutti i farmaci presenti nel database con una chiamata GET.</p>
   *
   * @return la lista di tutti i farmaci presenti nel Database
   */
  @GetMapping
  public List<Farmaco> getAll() {
    if (farmaci.getFarmaci().isEmpty()) {
      farmaci.initialize();
    }
    return farmaci.getFarmaci();
  }

  /**
   * <p>Questo metodo elimina la scheda farmaco con un determinato id.</p>
   *
   * @param id il codice della scheda farmaco da richiedere
   * @return il farmaco con l'id id
   */
  @GetMapping("/{id}")
  public Farmaco getById(@PathVariable String id) {
    if (farmaci.getFarmaci().isEmpty()) {
      farmaci.initialize();
    }
    return farmaci.findById(id);
  }
}