package com.example.FrontEnd.FrontEnd.model;

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
