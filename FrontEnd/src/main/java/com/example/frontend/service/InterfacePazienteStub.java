package com.example.frontend.service;

import com.example.frontend.model.Paziente;
import java.util.List;

/**
 * <p>Questa interfaccia viene utilizzata per prendere le informazioni
 * relative ai pazienti dallo stub esterno.</p>
 */
public interface InterfacePazienteStub {

  /**
   * <p>Questo metodo cerca un paziente in base al codice fiscale.</p>
   *
   * @param cf il codice fiscale del paziente
   * @return il paziente con quel determinato codice fiscale
   */
  public Paziente findByCf(String cf);

  /**
   * <p>Questo metodo restituisce una lista di paziente in base al filtro di ricerca inserito.</p>
   *
   * @param p i dati del paziente inseriti
   * @return una lista di pazienti
   */
  List<Paziente> findPazienti(Paziente p);

  /**
   * <p>Questo metodo restituisce una lista di tutti i pazienti.</p>
   *
   * @return una lista di tutti i pazienti
   */
  List<Paziente> findAllPazienti();
}
