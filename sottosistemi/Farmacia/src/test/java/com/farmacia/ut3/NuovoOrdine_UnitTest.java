package com.farmacia.ut3;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.exception.FormatoQuantitaNonCorrettaException;
import com.farmacia.exception.SchedaFarmacoNotFoundException;
import com.farmacia.model.Ordine;
import com.farmacia.repository.FarmaciaRepository;
import com.farmacia.repository.OrdineRepository;
import com.farmacia.service.FarmaciaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NuovoOrdine_UnitTest {
    @Autowired
    FarmaciaServiceImpl service;
    @Mock
    FarmaciaRepository farmaciaRepository;
    @Mock
    OrdineRepository ordineRepository;

    @BeforeEach
    void setUp() {
        farmaciaRepository = Mockito.mock(FarmaciaRepository.class);
        ordineRepository = Mockito.mock(OrdineRepository.class);
        service = new FarmaciaServiceImpl(farmaciaRepository, ordineRepository);
    }

    @Test
    void checkNuovoOrdineFarmacoNotFound() throws ParseException {
        //Richiesta dell'ordine da fare con codice errato
        OrdineRequest ordineRequest = new OrdineRequest("45", 150);

        Mockito.when(farmaciaRepository.existsById(ordineRequest.getCodiceFarmaco())).thenReturn(false);

        //Verifica se viene lanciata un'eccezione di tipo SchedaFarmacoNotFoundException
        assertThrows(
                SchedaFarmacoNotFoundException.class,
                () -> service.nuovoOrdine(ordineRequest),
                "Scheda Farmaco con CODICE: |" + ordineRequest.getCodiceFarmaco() + "| non trovato"
        );
    }

    @Test
    void checkNuovoOrdineFormatoQuantitàErrata(){
        //Richiesta dell'ordine da fare con quantità errata
        OrdineRequest ordineRequest = new OrdineRequest("1", -150);

        Mockito.when(farmaciaRepository.existsById(ordineRequest.getCodiceFarmaco())).thenReturn(true);

        //Verifica se viene lanciata un'eccezione di tipo FormatoQuantitaNonCorrettaException
        assertThrows(
                FormatoQuantitaNonCorrettaException.class,
                () -> service.nuovoOrdine(ordineRequest),
                "Formato quantità errato " + ordineRequest.getQuantita());

    }

    @Test
    void checkNuovoOrdineOk(){
        //Richiesta dell'ordine da fare
        OrdineRequest ordineRequest = new OrdineRequest("1", 1540);

        Mockito.when(farmaciaRepository.existsById(ordineRequest.getCodiceFarmaco())).thenReturn(true);


        Ordine ordineExpected = Ordine.builder()
                .codiceFarmaco(ordineRequest.getCodiceFarmaco())
                .quantita(ordineRequest.getQuantita())
                .stato(Ordine.STATO_DA_ACQUISTARE)
                .build();

        Mockito.when(ordineRepository.save(ordineExpected)).thenReturn(ordineExpected);

        //verifica se l'ordine viene inserito correttamente
        assertEquals(ordineExpected, service.nuovoOrdine(ordineRequest));
        }
}
