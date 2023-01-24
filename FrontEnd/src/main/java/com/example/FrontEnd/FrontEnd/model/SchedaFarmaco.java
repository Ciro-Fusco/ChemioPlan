package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * <p>Questa classe modella l'entità scheda farmaco.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaFarmaco {

  /**
   * <p>Codice della scheda farmaco.</p>
   */
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  private String nome;

  /**
   * <p>Quantità di farmaco presente in farmacia.</p>
   */
  private Integer quantita;

  /**
   * <p>Numero del lotto presente in farmacia.</p>
   */
  private Integer numeroLotto;

  /**
   * <p>Scadenza del lotto.</p>
   */
  private Date scadenzaLotto;
}
