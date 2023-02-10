package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe modella l'entità scheda farmaco.</p>
 *
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaFarmaco {

  /**
   * <p>Codice della scheda farmaco.</p>
   */

  @Size(min = 1, max = 10, message = "Lunghezza Errata")
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  @Size(min = 1, max = 256, message = "Lunghezza Errata")
  private String nome;

  /**
   * <p>Dosaggio del farmaco.</p>
   */
  @Min(value = 1, message = "Il Dosaggio deve essere Positivo")
  @NotNull(message = "Dimensione flacone obbligatorio")
  private Double dimensioneFlacone;

  /**
   * <p>Indica la durata del farmaco dopo l'apertura.</p>
   */
  @Min(value = 1, message = "La Scadenza deve essere Positivo")
  @NotNull(message = "Scadenza Obbligatorio")
  private Integer scadenzaDopoApertura;

  /**
   * <p>Lotti del farmaco presente in farmacia.</p>
   */
  private List<Lotto> lotti = new ArrayList<>();
}
