package com.farmacia.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lotto {
  private String codiceFarmaco;
  private Integer numeroLotto;
  private Date scadenzaLotto;
}
