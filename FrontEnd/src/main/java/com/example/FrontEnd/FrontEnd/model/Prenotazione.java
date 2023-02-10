package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.Future;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * <p>
 * uesta classe modella l'entità Prenotazione.
 * </p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Prenotazione {

  private String codice;

  @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}", message = "Codice Fiscale Invalido")
  private String codiceFiscale;

  @Future(message = "la data non puo essere nel passato")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  @NotNull(message = "Inserire Data Scadena")
  private Date data;

  @Pattern(regexp = "[A-z0-9]{3,256}|^.{0}", message = "Sala non valida")
  @NotEmpty(message = "Sala obbligatorio")
  private String sala;

  @Pattern(regexp = "[A-z0-9]{3,256}|^.{0}", message = "Poltrona non valida")
  @NotEmpty(message = "Poltrona obbligatorio")
  private String poltrona;

  private boolean confermata = false;
}
