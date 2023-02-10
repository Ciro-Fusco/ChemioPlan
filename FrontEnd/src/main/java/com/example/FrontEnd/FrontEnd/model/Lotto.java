package com.example.FrontEnd.FrontEnd.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * Questa classe modella il lotto della scheda farmaco.
 * </p>
 *
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lotto {
  /**
   * <p>
   * Numero del lotto.
   * </p>
   */
  @Min(value = 1, message = "Numero Lotto Invalido")
  @NotNull(message = "Inserire Numero Lotto")
  private Integer numeroLotto;

  /**
   * <p>
   * Data di scadenza del lotto.
   * </p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Future(message = "la data non puo essere nel passato")
  @NotNull(message = "Inserire Data Scadena")
  private Date scadenzaLotto;

  /**
   * <p>
   * Quantità di farmaco presente in farmacia per il relativo lotto.
   * </p>
   */
  @Min(value = 1, message = "Quantita lotto invalida")
  @NotNull(message = "Inserire Quantita Lotto")
  private Integer quantita;
}
