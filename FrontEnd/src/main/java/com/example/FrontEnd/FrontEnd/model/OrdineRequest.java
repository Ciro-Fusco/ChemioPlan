package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella il DTO ordine per le richieste.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrdineRequest {
  /**
   * <p>Codice della scheda farmaco.</p>
   */
  private String codiceFarmaco;

  /**
   * <p>Quantità da ordinare.</p>
   */
  private Integer quantita;
}
