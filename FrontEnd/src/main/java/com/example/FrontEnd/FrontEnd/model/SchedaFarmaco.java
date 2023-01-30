package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
//  @NotNull
  @Size(min=2, max=30,message = "Lunghezza Errata")
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  @Size(min=2, max=30,message = "Lunghezza Errata")
  private String nome;

  /**
   * <p>Dosaggio del farmaco</p>
   */
  @Min(value = 0,message = "Il Dosaggio deve essere Positivo")
  @NotNull(message = "Dosaggio Obbligatorio")
  private Double dosaggio;

  /**
   * <p>Indica la durata del farmaco dopo l'apertura.</p>
   */
  private Integer scadenzaDopoApertura;

  /**
   * <p>Lotti del farmaco presente in farmacia.</p>
   */
  private List<Lotto> lotti = new ArrayList<>();
}
