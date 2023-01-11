package com.farmacia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>Questa classe modella l'entità ordine.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "Ordini")
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
  @Id
  private String id;

  /**
   * <p>Codice della scheda farmaco.</p>
   */
  private String codiceFarmaco;

  /**
   * <p>Quantità da ordinare.</p>
   */
  private Integer quantita;

  /**
   * <p>Stato dell'ordine.</p>
   */
  private String stato;
}
