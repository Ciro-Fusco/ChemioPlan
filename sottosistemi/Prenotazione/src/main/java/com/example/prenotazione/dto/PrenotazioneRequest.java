package com.example.prenotazione.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Questa classe rappresenta il DTO per la richiesta.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

  private String codiceFiscale;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
  private Date data;

  private String sala;

  private String poltrona;

}
