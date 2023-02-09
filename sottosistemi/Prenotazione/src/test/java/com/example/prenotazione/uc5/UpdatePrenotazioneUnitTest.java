package com.example.prenotazione.uc5;

import com.example.prenotazione.exception.PrenotazioneNotFoundException;
import com.example.prenotazione.model.Prenotazione;
import com.example.prenotazione.repository.PrenotazioneRepository;
import com.example.prenotazione.service.PrenotazioneService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UpdatePrenotazioneUnitTest {
  @Autowired
  PrenotazioneService service ;
  @Mock
  PrenotazioneRepository repository;
  SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

  @Before
  void setUp() {
    repository = Mockito.mock(PrenotazioneRepository.class);
    service = new PrenotazioneService(repository);
  }

  @Test
  void checkUpadatePrenotazioneCodiceNotFound() throws ParseException {
    //prenotazione da aggiornare con codice errato
    Prenotazione prenotazioneExpected = new Prenotazione("63e107448ed417204f3064de", "RSSMRC75R13F839Q", smp.parse("2023-05-25T15:30"), "4", "2", false);

    //verifica se il metodo lancia un eccezione per il codice errato
    assertThrows(
        PrenotazioneNotFoundException.class,
        () -> service.updatePrenotazione(prenotazioneExpected.getCodice(), prenotazioneExpected),
        "Prenotazione con CODICE: |" + prenotazioneExpected.getCodice() + "| non trovata"
    );
  }


  @Test
  void checkUpadatePrenotazione() throws ParseException {
    //prenotazione da aggiornare
    Prenotazione prenotazioneExpected = new Prenotazione("63e107448ed417204f3064de", "RSSMRC75R13F839Q", smp.parse("2023-05-25T15:30"), "4","2", false );

    //aggiornamento della prenotazione nel DB
    service.updatePrenotazione(prenotazioneExpected.getCodice(), prenotazioneExpected);

    //verifico se l'aggiornamento è andato a buon fine
    assertEquals(service.mapToPrenotazioneResponse(prenotazioneExpected), service.getById("63e107448ed417204f3064de"));
  }
}