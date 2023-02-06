package com.chemioplan.SchedaPaziente.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * <p>Questa classe rappresenta il DTO per la risposta.</p>
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SchedaPazienteResponse {

  @Id
  private int id;
  private String codiceFiscale;
  private List<String> codiceFarmaci;
  private List<String> malattie;
}
