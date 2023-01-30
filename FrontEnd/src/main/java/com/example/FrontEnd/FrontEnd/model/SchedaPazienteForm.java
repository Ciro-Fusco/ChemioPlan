package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Questa classe rappresenta l'entità Paziente</p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaPazienteForm {
  /**
   * <p>CodiceFiscale dell paziente</p>
   */
  private String codiceFiscale;

  /**
   *<p>Codice dei farmaci che il paziente assume</p>
   */
  private List<String> farmaci = new ArrayList<>();

  /**
   * <p>nome delle malattie da cui è affetto il paziente</p>
   */
  private List<String> malattie = new ArrayList<>();

  public SchedaPaziente mapToSchedaPaziente() {
    SchedaPaziente scheda = new SchedaPaziente();
    scheda.setCodiceFiscale(this.codiceFiscale);
    scheda.setMalattie(this.malattie);

    int pos;
    HashMap<String,Double> map = new HashMap<>();
    for (String s : this.farmaci) {
      pos = s.indexOf('=');
      if (pos != -1) {
        String codice = s.substring(0, pos);
        Double dosaggio = Double.parseDouble(s.substring(pos + 1, s.length()));
        map.put(codice, dosaggio);
      }
    }
    scheda.setFarmaci(map);
    return scheda;
  }

  public static SchedaPazienteForm mapToSchedaPazienteForm(SchedaPaziente s) {
    SchedaPazienteForm scheda = new SchedaPazienteForm();
    scheda.setCodiceFiscale(s.getCodiceFiscale());
    scheda.setMalattie(s.getMalattie());

    List<String> list = new ArrayList<>();
    for (Object key: s.getFarmaci().keySet()) {
      String codice = (String) key;
      Double dosaggio = s.getFarmaci().get(key);
      list.add(codice + "=" + dosaggio);
    }

    scheda.setFarmaci(list);
    return scheda;
  }
}
