package com.example.FrontEnd.FrontEnd.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Questa classe rappresenta l'entità Paziente
 * </p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaPaziente {
  /**
   * <p>
   * CodiceFiscale dell paziente
   * </p>
   */
  @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}", message = "Codice Fiscale Invalido")
  private String codiceFiscale;

  /**
   * <p>Nome del paziente.</p>
   */
  @Pattern(regexp = "[A-z]{3,256}|^.{0}", message = "Nome non valido")
  private String nome;

  /**
   * <p>Cognome del paziente.</p>
   */
  @Pattern(regexp = "[A-z]{3,256}|^.{0}", message = "Cognome non valido")
  private String cognome;

  /**
   * <p>
   * Codice dei farmaci che il paziente assume
   * </p>
   */
  private HashMap<String, Double> farmaci = new HashMap<>();

  /**
   * <p>
   * nome delle malattie da cui è affetto il paziente
   * </p>
   */
  private List<String> malattie = new ArrayList<>();
}
