package com.example.apigateway.stub.archivio;

import com.example.apigateway.stub.model.Malattia;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.Data;

/**
 * <p>Archivo delle malattie che possono esserci.</p>
 *
 * @version 1.0
 */
@Data
public class ArchivioMalattia {

  List<Malattia> malattie = new ArrayList<>();

  /**
   * <p>Inizializza l'archivio.</p>
   */
  public void initialize() {
    malattie.add(new Malattia("codice1", "nome1", "testa"));
    malattie.add(new Malattia("codice2", "nome2", "polmoni"));
    malattie.add(new Malattia("codice3", "nome3", "pancreas"));
    malattie.add(new Malattia("codice4", "nome4", "cervello"));
    malattie.add(new Malattia("codice5", "nome5", "fegato"));
  }

  /**
   * <p>Cerca la malattia per codice.</p>
   *
   * @param codice codice della malattia
   * @return malattia
   */
  public Malattia findByCodice(String codice) {
    List<String> codici = malattie.stream().map((m) -> m.getCodiceMalattia()).toList();
    return malattie.get(codici.indexOf(codice));
  }

  /**
   * <p>Cerca i pazienti che hanno uno specifico nome.</p>
   *
   * @param nome nome del paziente
   * @return lista di malattie
   */
  public List<Malattia> findByNome(String nome) {
    Iterator<Malattia> iterator = malattie.listIterator();
    List malattieNome = new ArrayList<>();
    for (Malattia m : malattie) {
      if (m.getNomeMalattia().equals(nome)) {
        malattieNome.add(m);
      }
    }
    return malattieNome;
  }

  /**
   * <p>Cerca i pazienti che hanno una parte del corpo in comune.</p>
   *
   * @param parteCorpo parte del corpo
   * @return lista di malattie
   */
  public List<Malattia> findByParteCorpo(String parteCorpo) {
    Iterator<Malattia> iterator = malattie.listIterator();
    List malattieParteCorpo = new ArrayList<>();
    for (Malattia m : malattie) {
      if (m.getParteCorpo().equals(parteCorpo)) {
        malattieParteCorpo.add(m);
      }
    }
    return malattieParteCorpo;
  }
}
