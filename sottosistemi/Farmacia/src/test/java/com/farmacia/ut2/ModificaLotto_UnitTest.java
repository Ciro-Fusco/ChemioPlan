package com.farmacia.ut2;

import com.farmacia.exception.*;
import com.farmacia.model.Lotto;
import com.farmacia.model.SchedaFarmaco;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.OrdineRepository;
import com.farmacia.service.FarmaciaServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ModificaLotto_UnitTest {
    @Autowired
    FarmaciaServiceImpl service;
    @Mock
    FarmaciaRepository farmaciaRepository;
    @Mock
    OrdineRepository ordineRepository;

    SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        farmaciaRepository = Mockito.mock(FarmaciaRepository.class);
        ordineRepository = Mockito.mock(OrdineRepository.class);
        service = new FarmaciaServiceImpl(farmaciaRepository, ordineRepository);
    }

    @Test
    void checkModificaLotto_FarmacoNotFound() throws ParseException {
        //codice del farmaco di cui modificare un lotto, il codice è errato
        String codiceFarmaco = "-45";

        //lotto da modificare
        Lotto l = new Lotto(1, smp.parse("2024-05-29"), 1000);

        Mockito.when(farmaciaRepository.findById(codiceFarmaco)).thenReturn(Optional.empty());

        //verifica se il metodo lancia una SchedaFarmacoNotFoundException dato il codice del farmaco errato
        assertThrows(
                SchedaFarmacoNotFoundException.class,
                () -> service.modificaLotto(codiceFarmaco , l.getNumeroLotto(), l),
                "Scheda Farmaco con CODICE: |" + l.getNumeroLotto() + "| non trovato"
        );
    }

    @Test
    void checkModificaLotto_LottoNotFound() throws ParseException {
        //farmaco di cui modificare un lotto
        String codiceFarmaco = "10";
        SchedaFarmaco schedaFarmaco = new SchedaFarmaco(codiceFarmaco, "Bisolvon", 1000.00, 60, new ArrayList<>());
        Lotto l1 = new Lotto(14, smp.parse("2024-03-25"), 500);
        Lotto l2 = new Lotto(15, smp.parse("2024-01-19"), 1500);
        Lotto l3 = new Lotto(16, smp.parse("2024-07-29"), 750);
        schedaFarmaco.addLotto(l1);
        schedaFarmaco.addLotto(l2);
        schedaFarmaco.addLotto(l3);

        Mockito.when(farmaciaRepository.findById(codiceFarmaco)).thenReturn(Optional.of(schedaFarmaco));
        //lotto da modificare, il lotto non è presente
        Lotto l = new Lotto(45, smp.parse("2024-05-29"), 1000);

        //verifica se il metodo lancia un eccezione di tipo LottoNotFoundException
        assertThrows(
                LottoNotFoundException.class,
                () -> service.modificaLotto(codiceFarmaco , l.getNumeroLotto(), l),
                "Lotto numero " + l.getNumeroLotto() + " non trovato"
        );
    }

    @Test
    void checkModificaLotto_FormatoNonCorretto() throws ParseException {
        //farmaco di cui modificare un lotto
        String codiceFarmaco = "10";
        SchedaFarmaco schedaFarmaco = new SchedaFarmaco(codiceFarmaco, "Bisolvon", 1000.00, 60, new ArrayList<>());
        Lotto l1 = new Lotto(14, smp.parse("2024-03-25"), 500);
        Lotto l2 = new Lotto(15, smp.parse("2024-01-19"), 1500);
        Lotto l3 = new Lotto(16, smp.parse("2024-07-29"), 750);
        schedaFarmaco.addLotto(l1);
        schedaFarmaco.addLotto(l2);
        schedaFarmaco.addLotto(l3);

        Mockito.when(farmaciaRepository.findById(codiceFarmaco)).thenReturn(Optional.of(schedaFarmaco));
        //lotto da inserire con la quantità negativa
        Lotto l = new Lotto(14, smp.parse("2024-05-29"), -1000);

        //verifica se il metodo lancia un eccezione di tipo FormatoQuantitaNonCorrettaException
        assertThrows(
                FormatoQuantitaNonCorrettaException.class,
                () -> service.modificaLotto(codiceFarmaco, l.getNumeroLotto(), l),
                "Quantità negativa"
        );
    }

    @Test
    void checkModificaLotto_OldDate() throws ParseException {
        //farmaco di cui modificare un lotto
        String codiceFarmaco = "10";
        SchedaFarmaco schedaFarmaco = new SchedaFarmaco(codiceFarmaco, "Bisolvon", 1000.00, 60, new ArrayList<>());
        Lotto l1 = new Lotto(14, smp.parse("2024-03-25"), 500);
        Lotto l2 = new Lotto(15, smp.parse("2024-01-19"), 1500);
        Lotto l3 = new Lotto(16, smp.parse("2024-07-29"), 750);
        schedaFarmaco.addLotto(l1);
        schedaFarmaco.addLotto(l2);
        schedaFarmaco.addLotto(l3);

        Mockito.when(farmaciaRepository.findById(codiceFarmaco)).thenReturn(Optional.of(schedaFarmaco));

        //lotto da inserire con la data errata
        Lotto l = new Lotto(14, smp.parse("2021-05-29"), 1000);

        //verifica se viene lanciata un'eccezione di tipo OldDateException
        assertThrows(
                OldDateException.class,
                () -> service.modificaLotto(codiceFarmaco , l.getNumeroLotto(), l),
                "Data nel passato");
    }

    @Test
    void checkModificaLotto_Ok() throws ParseException {
        //Farmaco in cui modificare il lotto
        SchedaFarmaco schedaFarmaco = new SchedaFarmaco("10", "Bisolvon", 1000.00, 60, new ArrayList<>());
        Lotto l = new Lotto(16, smp.parse("2024-05-29"), 1500);
        schedaFarmaco.addLotto(l);

        Mockito.when(farmaciaRepository.findById(schedaFarmaco.getCodice())).thenReturn(Optional.of(schedaFarmaco));

        //Lotto da modificare
        Lotto lottoModifica = new Lotto(16, smp.parse("2025-05-29"), 1000);

        //modifica del lotto
        service.modificaLotto(schedaFarmaco.getCodice(), lottoModifica.getNumeroLotto(), lottoModifica);

        //verifica se la modifica è avvenuta correttamente
        assertEquals(lottoModifica, service.ottieniLotto(schedaFarmaco.getCodice(), l.getNumeroLotto()));
    }
}