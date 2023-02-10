package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>
 * Questa classe modella la malattia.
 * </p>
 *
 * @version 0.1
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Malattia {
  /**
   * <p>CodiceMalattia.</p>
   */
  private String codiceMalattia;
  /**
   * <p>NomeMalattia.</p>
   */
  private String nomeMalattia;
  /**
   * <p>ParteCorpo.</p>
   */
  private String parteCorpo;

}