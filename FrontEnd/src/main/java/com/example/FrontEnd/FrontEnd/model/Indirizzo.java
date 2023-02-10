package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella le informazioni relative all'indirizzo.</p>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indirizzo {
  /**
   * <p>Via.</p>
   */
  private String via;
  /**
   * <p>Città.</p>
   */
  private String citta;
  /**
   * <p>Paese.</p>
   */
  private String paese;
  /**
   * <p>Cap.</p>
   */
  private String cap;
}