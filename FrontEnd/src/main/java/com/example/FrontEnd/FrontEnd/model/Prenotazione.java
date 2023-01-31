package com.example.FrontEnd.FrontEnd.model;

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
 * <p>uesta classe modella l'entità Prenotazione.</p>
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
  private String codiceFiscale;
  @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
  private Date data;
  private String sala;
  private String poltrona;
  private List<String> codiceFarmaci = new ArrayList<>();

}
