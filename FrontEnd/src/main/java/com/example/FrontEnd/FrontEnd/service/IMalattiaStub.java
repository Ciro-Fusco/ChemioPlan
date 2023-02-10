package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Malattia;

/**
 * <p>Questa interfaccia viene utilizzata per prendere le informazioni
 * relative alle malattie dallo stub esterno.</p>
 */
public interface IMalattiaStub {

  /**
   * <p>Questo metodo restituisce una malattia con un determinato codice.</p>
   *
   * @param codice codice della malattia
   */
  Malattia getMalattia(String codice);

  /**
   * <p>Questo metodo restituisce una lista di malattie.</p>
   */
  Malattia[] getAll();
}
