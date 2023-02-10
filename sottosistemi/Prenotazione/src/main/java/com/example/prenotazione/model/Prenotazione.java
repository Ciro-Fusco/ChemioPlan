package com.example.prenotazione.model;


import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>uesta classe modella l'entità Prenotazione.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Document(value = "prenotazione")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Prenotazione {
  /**
   * <p>Codice.</p>
   */
  @Id
  private String codice;

  /**
   * <p>CodceFiscale.</p>
   */
  @Field(name = "codiceFiscale")
  private String codiceFiscale;

  /**
   * <p>Data Prenotazione.</p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @Field(name = "data")
  private Date data;

  /**
   * <p>Sala.</p>
   */
  @Field(name = "sala")
  private String sala;

  /**
   * <p>Poltrona.</p>
   */
  @Field(name = "poltrona")
  private String poltrona;

  /**
   * <p>Confermata.</p>
   */
  @Field(name = "confermata")
  private boolean confermata;

}
