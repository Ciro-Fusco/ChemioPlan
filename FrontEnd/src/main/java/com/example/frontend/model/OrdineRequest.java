package com.example.frontend.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella il DTO ordine per le richieste.</p>
 *
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
  @Size(min = 1, max = 10, message = "Formato Codice Farmaco Errato")
  @NotNull(message = "Inserire Codice Farmaco")
  private String codiceFarmaco;

  /**
   * <p>Quantità da ordinare.</p>
   */
  @Min(value = 1, message = "Quantita ordine errata")
  @NotNull(message = "Quantita obligatoria")
  private Integer quantita;
}
