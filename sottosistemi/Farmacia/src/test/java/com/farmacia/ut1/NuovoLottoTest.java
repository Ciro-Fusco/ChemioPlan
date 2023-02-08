package com.farmacia.ut1;

import com.farmacia.exception.FormatoQuantitaNonCorrettaException;
import com.farmacia.exception.LottoAlreadyExistException;
import com.farmacia.exception.OldDateException;
import com.farmacia.exception.SchedaFarmacoNotFoundException;
import com.farmacia.model.Lotto;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.OrdineRepository;
import com.farmacia.service.FarmaciaServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NuovoLottoTest {
  @Autowired
  FarmaciaServiceImpl service;
  @Mock
  FarmaciaRepository farmaciaRepository;
  @Mock
  OrdineRepository ordineRepository;

  SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
  @Before
  void setUp() {
    farmaciaRepository = Mockito.mock(FarmaciaRepository.class);
    ordineRepository = Mockito.mock(OrdineRepository.class);
    service = new FarmaciaServiceImpl(farmaciaRepository, ordineRepository);
  }

  @Test
  void checkNuovoLottoFarmacoNotFound() throws ParseException {
    //codice del farmaco di cui inserire un nuovo lotto
    String codiceFarmaco = "45";

    //lotto da inserire
    Lotto l = new Lotto(1, smp.parse("2024-05-29"), 1000);

    //inserimento del lotto
    assertThrows(
            SchedaFarmacoNotFoundException.class,
            () -> service.nuovoLotto(codiceFarmaco , l),"Scheda Farmaco con CODICE: |" + codiceFarmaco + "| non trovato"
    );
  }

  @Test
  void checkNuovoLottoLottoAlreadyExist() throws ParseException {
    //codice del farmaco di cui inserire un nuovo lotto
    String codiceFarmaco = "FRM1";

    //lotto da inserire
    Lotto l = new Lotto(1, smp.parse("2024-05-29"), 1000);

    assertThrows(
            LottoAlreadyExistException.class,
            () -> service.nuovoLotto(codiceFarmaco , l),
            "Lotto numero |" + l.getNumeroLotto() + "| già presente nella lista"
    );
  }

  @Test
  void checkNuovoLottoOldDateException() throws ParseException {
    //codice del farmaco di cui inserire un nuovo lotto
    String codiceFarmaco = "FRM1";

    //lotto da inserire
    Lotto l = new Lotto(16, smp.parse("2023-01-29"), 1000);

    //verifica del corretto inserimento del lotto
    assertThrows(
            OldDateException.class,
            () -> service.nuovoLotto(codiceFarmaco, l),
            "Data nel passato"
    );
  }

  @Test
  void checkNuovoLottoFormatoQuantitaException() throws ParseException {
    //codice del farmaco di cui inserire un nuovo lotto
    String codiceFarmaco = "FRM1";

    //lotto da inserire
    Lotto l = new Lotto(15, smp.parse("2024-01-29"), -2);

    //verifica del corretto inserimento del lotto
    assertThrows(
            FormatoQuantitaNonCorrettaException.class,
            () -> service.nuovoLotto(codiceFarmaco, l),
            "Quantità negativa"
    );
  }

  @Test
  void checkNuovoLottoOk() throws ParseException {
    //codice del farmaco di cui inserire un nuovo lotto
    String codiceFarmaco = "FRM1";

    //lotto da inserire
    Lotto l = new Lotto(16, smp.parse("2023-05-29"), 1000);

    //inserimento del lotto
    service.nuovoLotto(codiceFarmaco , l);

    //verifica del corretto inserimento del lotto
    assertEquals(l , service.ottieniLotto(codiceFarmaco,l.getNumeroLotto()));
  }
}
