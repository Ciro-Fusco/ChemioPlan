package com.farmacia.service;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.model.Lotto;
import com.farmacia.model.Ordine;
import com.farmacia.model.SchedaFarmaco;
import java.util.List;

public interface FarmaciaService {
  List<SchedaFarmaco> ottieniFarmaci();

  SchedaFarmaco ottieniFarmacoPerCodice(String codice);

  void aggiungiFarmaco(SchedaFarmaco schedaFarmaco);

  void modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco);

  void eliminaFarmaco(String codice);

  void nuovoLotto(Lotto lotto);

  Ordine nuovoOrdine(OrdineRequest ordine);

  Ordine ottieniOrdine(String id);

  void ordineConsegnato(String id);
}
