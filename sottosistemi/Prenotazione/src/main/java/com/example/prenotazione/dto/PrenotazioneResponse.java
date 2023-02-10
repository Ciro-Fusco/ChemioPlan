package com.example.prenotazione.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Questa classe rappresenta il DTO per la risposta.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {

  /**
   * <p>Codice.</p>
   */
  @Id
  private String codice;

  /**
   * <p>CodceFiscale.</p>
   */
  private String codiceFiscale;

  /**
   * <p>Data Prenotazione.</p>
   */
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date data;

  /**
   * <p>Sala.</p>
   */
  private String sala;

  /**
   * <p>Poltrona.</p>
   */
  private String poltrona;

  /**
   * <p>Confermata.</p>
   */
  private boolean confermata;

}
