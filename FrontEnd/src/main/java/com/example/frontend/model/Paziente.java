package com.example.frontend.model;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Questa classe modella l'entità paziente.</p>
 *
 * @version 0.1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paziente {

  /**
   * <p>CodiceFiscale.</p>
   */
  @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}|^.{0}",
          message = "Codice Fiscale Invalido")
  private String codiceFiscale;

  /**
   * <p>Nome.</p>
   */
  private String nome;

  /**
   * <p>Cognome.</p>
   */
  private String cognome;

  /**
   * <p>Data di nascita.</p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String dataNascita;

  /**
   * <p>Indirizzo.</p>
   */
  private Indirizzo indirizzo;

  /**
   * <p>Luogo di nascita.</p>
   */
  private String luogoNascita;
}
