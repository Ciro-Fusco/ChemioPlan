package com.example.FrontEnd.FrontEnd.model;

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
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  private String nome;

  /**
   * <p>Dosaggio del farmaco</p>
   */
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
