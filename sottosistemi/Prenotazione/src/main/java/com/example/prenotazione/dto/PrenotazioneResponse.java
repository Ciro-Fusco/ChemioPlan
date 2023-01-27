package com.example.prenotazione.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

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

  @Id
  private String codice;
  private String codiceFiscale;
  private String data;
  private String sala;
  private String poltrona;
  private List<String> codiceFarmaci;
}
