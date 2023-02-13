package com.chemioplan.schedapaziente.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> Questa classe rappresenta il DTO per la richiesta.</p>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedaPazienteRequest {
  private String codiceFiscale;
  private List<String> codiceFarmaci;
  private List<String> malattie;
}
