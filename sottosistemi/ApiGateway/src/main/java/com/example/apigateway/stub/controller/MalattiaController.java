package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.ArchivioMalattia;
import com.example.apigateway.stub.model.Malattia;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>La classe MalattiaController è responsabile dell'elaborazione delle richieste in entrata.</p>
 */
@RestController
@RequestMapping("/fhir/malattia")
public class MalattiaController {
  /**
   * <p>Riferimento alla classe ArchivioMalattia.</p>
   */
  ArchivioMalattia malattie = new ArchivioMalattia();

  /**
   * <p>Questo metodo restituisce tutti le malattie presenti nel database con una chiamata GET.</p>
   *
   * @return la lista di tutte le malattie presenti nel Database
   */
  @GetMapping
  public List<Malattia> getAll() {
    if (malattie.getMalattie().isEmpty()) {
      malattie.initialize();
    }
    return malattie.getMalattie();
  }

  /**
   * <p>Questo metodo restituisce tutti la malattie con un determinato codice.</p>
   *
   * @param codice il codice della malattia da richiedere
   * @return la malattia richiesta
   */
  @GetMapping("/codice/{codice}")
  public Malattia getByCodice(@PathVariable String codice) {
    if (malattie.getMalattie().isEmpty()) {
      malattie.initialize();
    }
    return malattie.findByCodice(codice);
  }

  /**
   * <p>Questo metodo restituisce tutti la malattie con un determinato nome.</p>
   *
   * @param nome il nome della malattia da richiedere
   * @return la malattia richiesta
   */
  @GetMapping("/nome/{nome}")
  public List<Malattia> getByNome(@PathVariable String nome) {
    if (malattie.getMalattie().isEmpty()) {
      malattie.initialize();
    }
    return malattie.findByNome(nome);
  }

  /**
   * <p>Questo metodo restituisce tutti le malattie di una determinata parte del corpo.</p>
   *
   * @param parteCorpo il nome della parte del corpo
   * @return la malattia richiesta
   */
  @GetMapping("/parteCorpo/{parteCorpo}")
  public List<Malattia> getByParteCorpo(@PathVariable String parteCorpo) {
    if (malattie.getMalattie().isEmpty()) {
      malattie.initialize();
    }
    return malattie.findByParteCorpo(parteCorpo);
  }
}
