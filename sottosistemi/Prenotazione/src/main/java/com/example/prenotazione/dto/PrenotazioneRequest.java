package com.example.prenotazione.dto;

import com.example.prenotazione.model.FarmacoDosaggio;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
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
  @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
  private Date data;
  private String sala;
  private String poltrona;
  private List<String> codiceFarmaci;
}
