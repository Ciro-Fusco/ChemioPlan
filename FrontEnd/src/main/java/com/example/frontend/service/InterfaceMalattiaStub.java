package com.example.frontend.service;

import com.example.frontend.model.Malattia;

/**
 * <p>Questa interfaccia viene utilizzata per prendere le informazioni
 * relative alle malattie dallo stub esterno.</p>
 */
public interface InterfaceMalattiaStub {

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
