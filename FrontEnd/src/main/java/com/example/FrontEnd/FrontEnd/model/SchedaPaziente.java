package com.example.FrontEnd.FrontEnd.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>Questa classe rappresenta l'entità Paziente</p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaPaziente {
  /**
   * <p>CodiceFiscale dell paziente</p>
   */
  private String codiceFiscale;

  /**
   *<p>Codice dei farmaci che il paziente assume</p>
   */
  private List<String> codiceFarmaci = new ArrayList<>();

  /**
   * <p>nome delle malattie da cui è affetto il paziente</p>
   */
  private List<String> malattie = new ArrayList<>();
}
