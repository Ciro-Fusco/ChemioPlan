package com.example.utente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>Questa classse modella l'entità Utente.</p>
 *
 * @author chris
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Utente")
public class Utente {
  /**
   * <p>id dell' Utente.</p>
   */
  @Id @Field("_id")
  private Integer id;
  /**
   * <p>nome dell' Utente.</p>
   */
  private String nome;
  /**
   * <p>cognome dell' Utente.</p>
   */
  private String cognome;
  /**
   * <p>credenziali dell' Utente.</p>
  */
  private Credenziali credenziali;
  /**
   * <p>ruolo dell' Utente.</p>
  */
  private String ruolo;
}
