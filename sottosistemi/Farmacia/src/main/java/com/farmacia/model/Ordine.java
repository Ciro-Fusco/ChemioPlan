package com.farmacia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(collection = "Ordini")
public class Ordine {
  public static final String STATO_DA_ACQUISTARE = "da acquistare";
  public static final String STATO_CONSEGNATO = "consegnato";

  @Id
  private String id;
  private String codiceFarmaco;
  private Integer quantita;
  private String stato;
}
