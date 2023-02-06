package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.*;

public interface IFarmaciaService {

    SchedaFarmaco getFarmaco(String id);

    SchedaFarmaco[] getAllFarmaci();

    String addFarmaco(SchedaFarmaco scheda);

    String nuovoLotto(String codice, Lotto lotto);

    String modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco);

    Lotto getLotto(String codiceFarmaco, Integer numeroLotto);

    String modificaLotto(String codiceFarmaco, Lotto lotto);

    String nuovoOrdine(OrdineRequest ordine);

    Ordine[] getAllOrdini();

    String eliminaFarmaco(String codice);
}
