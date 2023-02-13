package com.example.frontend.model;

import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * <p>Questa classe rappresenta l'entità Paziente.</p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchedaPazienteForm {
  /**
   * <p></p>CodiceFiscale dell paziente.</p>
   */
  @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}|^.{0}",
          message = "Codice Fiscale Invalido")
  private String codiceFiscale;

  /**
   * <p>Nome del paziente.</p>
   */
  @Pattern(regexp = "[A-z]{3,256}|^.{0}", message = "Nome non valido")
  private String nome;

  /**
   * <p>Cognome del paziente.</p>
   */
  @Pattern(regexp = "[A-z]{3,256}|^.{0}", message = "Cognome non valido")
  private String cognome;

  /**
   * <p>Codice dei farmaci che il paziente assume.</p>
   */
  private List<String> farmaci = new ArrayList<>();

  /**
   * <p>Codici delle malattie da cui è affetto il paziente.</p>
   */
  private List<String> malattie = new ArrayList<>();

  /**
   * <p>Trasforma l'oggetto schediaPazienteForm in una schedaPaziente.</p>
   *
   * @return scheda
   */
  public SchedaPaziente mapToSchedaPaziente() {
    SchedaPaziente scheda = new SchedaPaziente();
    scheda.setCodiceFiscale(this.codiceFiscale);
    scheda.setMalattie(this.malattie);
    scheda.setNome(this.nome);
    scheda.setCognome(this.cognome);

    int pos;
    HashMap<String, Double> map = new HashMap<>();
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

  /**
   * <p>Trasforma l'oggetto schediaPaziente in una schedaPazienteForm.</p>
   *
   * @return scheda
   */
  public static SchedaPazienteForm mapToSchedaPazienteForm(SchedaPaziente s) {
    SchedaPazienteForm scheda = new SchedaPazienteForm();
    scheda.setCodiceFiscale(s.getCodiceFiscale());
    scheda.setMalattie(s.getMalattie());
    scheda.setNome(s.getNome());
    scheda.setCognome(s.getCognome());

    List<String> list = new ArrayList<>();
    for (Object key : s.getFarmaci().keySet()) {
      String codice = (String) key;
      Double dosaggio = s.getFarmaci().get(key);
      list.add(codice + "=" + dosaggio);
    }
    scheda.setFarmaci(list);
    return scheda;
  }
}
