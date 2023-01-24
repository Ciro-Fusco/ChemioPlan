package com.example.prenotazione.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * <p>uesta classe modella l'entità Prenotazione.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Document(value = "prenotazioni")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Prenotazione {

  @Id
  @Field(name = "id")
  private String codice;
  @Field(name = "data")
  private String data;
  @Field(name = "sala")
  private String sala;
  @Field(name = "poltrona")
  private String poltrona;
  @Field(name = "codicefarmaci")
  private List<String> codiceFarmaci;
}
