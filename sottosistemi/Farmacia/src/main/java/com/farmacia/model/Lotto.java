package com.farmacia.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
   * <p>Numero del lotto.</p>
   */
  private Integer numeroLotto;

  /**
   * <p>Data di scadenza del lotto.</p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date scadenzaLotto;

  /**
   * <p>Quantità di farmaco presente in farmacia per il relativo lotto.</p>
   */
  private Integer quantita;
}
