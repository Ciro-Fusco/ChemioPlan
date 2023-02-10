package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella l'entità ordine.</p>
 *
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Ordine {
  /**
   * <p>Stringa costante che indica lo stato 'da acquistare' dell'ordine.</p>
   */
  public static final String STATO_DA_ACQUISTARE = "da acquistare";

  /**
   * <p>Stringa costante che indica lo stato 'consegnato' dell'ordine.</p>
   */
  public static final String STATO_CONSEGNATO = "consegnato";

  /**
   * <p>Id dell'ordine.</p>
   */
  private String id;

  /**
   * <p>Codice della scheda farmaco.</p>
   */
  @Size(min = 1, max = 10, message = "Formato Codice Farmaco Errato")
  @NotNull(message = "Inserire Codice Farmaco")
  private String codiceFarmaco;
  /**
   * <p>Quantità da ordinare.</p>
   */
  @Min(value = 1, message = "Quantita minima ordine errata")
  private Integer quantita;

  /**
   * <p>Stato dell'ordine.</p>
   */
  private String stato;
}
