package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.ArchivioPazienti;
import com.example.apigateway.stub.model.Paziente;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>La classe PazienteController è responsabile dell'elaborazione delle richieste in entrata.</p>
 */
@RestController
@RequestMapping("/fhir/paziente")
public class PazienteController {
  /**
   * <p>Riferimento alla classe ArchivioPazienti.</p>
   */
  private ArchivioPazienti pazienti = new ArchivioPazienti();

  /**
   * <p>Questo metodo restituisce tutti i pazienti presenti nel database con una chiamata GET.</p>
   *
   * @return la lista di tutte i pazienti presenti nel Database
   */
  @GetMapping
  public List<Paziente> getAll() {
    if (pazienti.getPazienti().isEmpty()) {
      pazienti.initialize();
    }
    for (Paziente p : pazienti.getPazienti()) {
      System.out.println(p.getDataNascita());
    }
    System.out.println();
    return pazienti.getPazienti();
  }

  /**
   * <p>Questo metodo restituisce il paziente con il codice fiscale cf.</p>
   *
   * @param cf codice fiscale del paziente
   * @return il paziente richiesto
   */
  @GetMapping("/{cf}")
  public Paziente getByCf(@PathVariable String cf) {
    if (pazienti.getPazienti().isEmpty()) {
      pazienti.initialize();
    }
    return pazienti.findByCf(cf);
  }

  /**
   * <p>Questo metodo restituisce il paziente partendo dal paziente.</p>
   *
   * @param p paziente
   * @return il paziente richiesto
   */
  @PostMapping("/trova-paziente")
  public List<Paziente> findPaziente(@RequestBody Paziente p) {
    if (pazienti.getPazienti().isEmpty()) {
      pazienti.initialize();
    }
    System.out.println(p.toString());
    List<Paziente> paz = new ArrayList<>();

    if (p.getNome() != null && p.getCognome() != null
            && p.getDataNascita() != null && p.getLuogoNascita() != null) {
      paz = pazienti.findByNomeCognomeDataLuogo(p.getNome(), p.getCognome(),
              p.getDataNascita(), p.getLuogoNascita());
    }
    if (p.getNome() != null && p.getCognome() != null
            && p.getDataNascita() != null) {
      paz = pazienti.findByNomeCognomeData(p.getNome(), p.getCognome(), p.getDataNascita());
    }
    if (p.getNome() != null && p.getCognome() != null
            && p.getLuogoNascita() != null) {
      paz = pazienti.findByNomeCognomeData(p.getNome(), p.getCognome(), p.getLuogoNascita());
    }
    if (p.getNome() != null && p.getCognome() != null) {
      System.out.println("qui ok");
      paz = pazienti.findByNomeCognome(p.getNome(), p.getCognome());
    }

    if (!p.getCodiceFiscale().equals("")) {
      paz.add(pazienti.findByCf(p.getCodiceFiscale()));
    }

    System.out.println(paz);
    return paz;
  }
}
