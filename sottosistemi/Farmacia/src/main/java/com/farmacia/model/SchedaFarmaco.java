package com.farmacia.model;

import java.util.ArrayList;
import java.util.List;

import com.farmacia.exception.LottoAlreadyExistException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>Questa classe modella l'entità scheda farmaco.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "farmacia")
public class SchedaFarmaco {

  /**
   * <p>Codice della scheda farmaco.</p>
   */
  @Id @Field("_id")
  private String codice;

  /**
   * <p>Nome del farmaco.</p>
   */
  private String nome;

  /**
   * <p>Dosaggio del farmaco per un paziente</p>
   */
  private Double dosaggio;

  /**
   * <p>Lotti dei farmaci</p>
   */
  private List<Lotto> lotti = new ArrayList<>();

  /**
   * <p>Aggiunge un nuovo lotto per il farmaco</p>
   *
   * @param lotto
   */
  public void addLotto(Lotto lotto) {
    this.lotti.add(lotto);
  }

  /**
   * <p>Rimuove un lotto per il farmaco</p>
   *
   * @param lotto
   */
  public void removeLotto(Lotto lotto) {
    this.lotti.remove(lotto);
  }

  /**
   * <p>Ci dice se un lotto è già presente nella lista</p>
   * @param lotto
   * @return true se il lotto è già contenuto, false altrimenti
   */
  public boolean lottiContains(Lotto lotto){
    List<Integer> numeriLotti = lotti.stream().map((l) -> l.getNumeroLotto()).toList();
    if (numeriLotti.contains(lotto.getNumeroLotto())){
      return true;
    }
    return false;
  }
}
