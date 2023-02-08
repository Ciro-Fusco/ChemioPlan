package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
public class Utente {
  /**
   * <p>id dell' Utente.</p>
   */
  @NotNull(message = "Id obbligatorio")
  private Integer id;
  /**
   * <p>nome dell' Utente.</p>
   */
  @NotEmpty(message = "Nome obbligatorio")
  private String nome;
  /**
   * <p>cognome dell' Utente.</p>
   */
  @NotEmpty(message = "Cognome obbligatorio")
  private String cognome;
  /**
   * <p>credenziali dell' Utente.</p>
  */
  @Valid
  private Credenziali credenziali;
  /**
   * <p>ruolo dell' Utente.</p>
  */
  @NotNull(message = "Ruolo obbligatorio")
  private String ruolo;
}
