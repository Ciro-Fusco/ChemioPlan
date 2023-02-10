package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * <p>
 * uesta classe modella l'entità Prenotazione.
 * </p>
 *
 * @version n.1 (10-01-2023)
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Prenotazione {
  /**
   * <p>Codice.</p>
   */
  private String codice;

  /**
   * <p>CodceFiscale.</p>
   */
  @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}",
          message = "Codice Fiscale Invalido")
  private String codiceFiscale;

  /**
   * <p>Data.</p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @NotNull(message = "Data obbligatorio")
  private Date data;

  /**
   * <p>Sala.</p>
   */
  @NotEmpty(message = "Sala obbligatorio")
  private String sala;
  /**
   * <p>Poltrona.</p>
   */
  @NotEmpty(message = "Poltrona obbligatorio")
  private String poltrona;
  private boolean confermata = false;
}
