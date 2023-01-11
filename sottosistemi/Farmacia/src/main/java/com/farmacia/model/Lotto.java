package com.farmacia.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella il lotto della scheda farmaco.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lotto {
  /**
   * <p>Codice della scheda farmaco.</p>
   */
  private String codiceFarmaco;

  /**
   * <p>Numero del lotto.</p>
   */
  private Integer numeroLotto;

  /**
   * <p>Data di scadenza del lotto.</p>
   */
  private Date scadenzaLotto;
}
