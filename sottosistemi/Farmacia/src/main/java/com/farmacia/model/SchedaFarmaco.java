package com.farmacia.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>Questa classe modella l'entità scheda farmaco.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Farmacia")
public class SchedaFarmaco {

  /**
   * <p>Codice della scheda farmaco.</p>
   */
  @Id @Field("_id")
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  private String nome;

  /**
   * <p>Quantità di farmaco presente in farmacia.</p>
   */
  private Integer quantita;

  /**
   * <p>Numero del lotto presente in farmacia.</p>
   */
  private Integer numeroLotto;

  /**
   * <p>Scadenza del lotto.</p>
   */
  private Date scadenzaLotto;
}
