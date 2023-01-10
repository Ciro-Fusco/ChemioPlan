package com.farmacia.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Farmacia")
public class SchedaFarmaco {
  @Id @Field("_id")
  private String codice;
  private String nome;
  private Integer quantita;
  private Integer numeroLotto;
  private Date scadenzaLotto;
}
