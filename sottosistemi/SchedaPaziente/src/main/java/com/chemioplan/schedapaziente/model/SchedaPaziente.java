package com.chemioplan.schedapaziente.model;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>Questa classe rappresenta l'entità Paziente.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pazienti")

public class SchedaPaziente {

  /**
   * <p>CodiceFiscale dell paziente.</p>
   */
  @Id
  @Field("_id")
  private String codiceFiscale;

  /**
   * <p>Nome del paziente.</p>
   */
  private String nome;

  /**
   * <p>Cognome del paziente.</p>
   */
  private String cognome;

  /**
   * <p>Codice dei farmaci che il paziente assume.</p>
   */
  @Field(name = "farmaci")
  private HashMap<String, Double> farmaci;

  /**
   * <p>nome delle malattie da cui è affetto il paziente.</p>
   */
  @Field(name = "malattie")
  private List<String> malattie;
}
