package com.example.prenotazione.uc5;
import com.example.prenotazione.exception.PrenotazioneNotFoundException;
import com.example.prenotazione.model.Prenotazione;
import com.example.prenotazione.repository.PrenotazioneRepository;
import com.example.prenotazione.service.PrenotazioneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UpdatePrenotazioneUnitTest {
  @Autowired
  PrenotazioneService service ;
  @Mock
  PrenotazioneRepository repository;
  SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(PrenotazioneRepository.class);
    service = new PrenotazioneService(repository);
  }

  @Test
  void checkUpadatePrenotazioneCodiceNotFound() throws ParseException {
    //prenotazione da aggiornare con codice errato
    String codicePrenotazione = "63e107448ed417204f3064de";
    Prenotazione prenotazioneExpected = new Prenotazione(codicePrenotazione, "RSSMRC75R13F839Q", smp.parse("2023-05-25T15:30"), "4", "2", false);

    Mockito.when(repository.findById(codicePrenotazione)).thenReturn(Optional.empty());

    //verifica se il metodo lancia un eccezione per il codice errato
    assertThrows(
        PrenotazioneNotFoundException.class,
        () -> service.updatePrenotazione(codicePrenotazione, prenotazioneExpected),
        "Prenotazione con CODICE: |" + codicePrenotazione + "| non trovata"
    );
  }


  @Test
  void checkUpadatePrenotazione() throws ParseException {
    //prenotazione da aggiornare
    String codicePrenotazione = "63e107448ed417204f3064de";
    Prenotazione prenotazioneExpected = new Prenotazione(codicePrenotazione, "RSSMRC75R13F839Q", smp.parse("2023-05-25T15:30"), "4","2", false );

    Mockito.when(repository.findById(codicePrenotazione)).thenReturn(Optional.of(prenotazioneExpected));

    //aggiornamento della prenotazione nel DB
    service.updatePrenotazione(prenotazioneExpected.getCodice(), prenotazioneExpected);

    //verifico se l'aggiornamento Ã¨ andato a buon fine
    assertEquals(service.mapToPrenotazioneResponse(prenotazioneExpected), service.getById(codicePrenotazione));
  }
}