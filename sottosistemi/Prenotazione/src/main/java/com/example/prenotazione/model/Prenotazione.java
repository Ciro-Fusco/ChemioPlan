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

  @Id
  private String codice;

  @Field(name = "codiceFiscale")
  private String codiceFiscale;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @Field(name = "data")
  private Date data;

  @Field(name = "sala")
  private String sala;

  @Field(name = "poltrona")
  private String poltrona;

  @Field(name = "confermata")
  private boolean confermata;

}
