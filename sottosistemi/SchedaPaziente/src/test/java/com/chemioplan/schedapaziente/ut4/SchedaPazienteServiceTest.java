package com.chemioplan.schedapaziente.ut4;

import com.chemioplan.schedapaziente.exception.SchedaPazienteNotFoundException;
import com.chemioplan.schedapaziente.model.SchedaPaziente;
import com.chemioplan.schedapaziente.repository.SchedaPazienteRepository;
import com.chemioplan.schedapaziente.service.SchedaPazienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SchedaPazienteServiceTest {
  @Autowired
  SchedaPazienteService service;
  @Mock
  SchedaPazienteRepository repository;

  @BeforeEach
  void setUp(){
    repository = Mockito.mock(SchedaPazienteRepository.class);
    service = new SchedaPazienteService(repository);
  }

  @Test
  void checkOttieniSchedaPazienteCodiceFiscaleNonTrovato() {
    //codice fiscale per la ricerca
    String cf = "aR13vaefwafwa839Q";

    //when
    when(repository.findById(cf)).thenReturn(Optional.empty());

    //verifica se viene lancia un eccezione di tipo SchedaPazienteNotFoundException
    assertThrows(
            SchedaPazienteNotFoundException.class,
            () -> service.ottieniSchedaPazientePerCodiceFiscale(cf),
            "SchedaPaziente con codiceFiscale " + cf +" non trovata"
    );
  }

  @Test
  void checkFindByNomeAndCognomeAndFarmaci(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    HashMap<String, Double> hashMap = new HashMap<>();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setNome("Marco");
    schedaRicerca.setCognome("Rossi");
    hashMap.put("farmaco1", 100.00);
    hashMap.put("farmaco2", 200.00);
    schedaRicerca.setFarmaci(hashMap);

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Marco");
    scheda1.setCognome("Rossi");
    scheda1.setFarmaci(hashMap);

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");
    scheda2.setFarmaci(hashMap);

    SchedaPaziente scheda3 = new SchedaPaziente();
    scheda3.setCodiceFiscale("codiceFiscale2");
    scheda3.setNome("Michele");
    scheda3.setCognome("Rossi");
    scheda3.setFarmaci(hashMap);

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByNomeAndCognomeAndFarmaci(schedaRicerca.getNome(), schedaRicerca.getCognome(), "farmaco1")).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerNomeFarmaci(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    HashMap<String, Double> hashMap = new HashMap<>();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setNome("Marco");
    hashMap.put("farmaco1", 100.00);
    hashMap.put("farmaco2", 200.00);
    schedaRicerca.setFarmaci(hashMap);

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Marco");
    scheda1.setCognome("Rossi");
    scheda1.setFarmaci(hashMap);

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Verdi");
    hashMap.put("farmaco3", 100.00);
    scheda2.setFarmaci(hashMap);

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByNomeAndFarmaci(schedaRicerca.getNome(), "farmaco1")).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerCognomeFarmaci(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    HashMap<String, Double> hashMap = new HashMap<>();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setCognome("Rossi");
    hashMap.put("farmaco1", 100.00);
    hashMap.put("farmaco2", 200.00);
    schedaRicerca.setFarmaci(hashMap);

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("MIchele");
    scheda1.setCognome("Rossi");
    scheda1.setFarmaci(hashMap);

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");
    hashMap.put("farmaco3", 100.00);
    scheda2.setFarmaci(hashMap);

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByCognomeAndFarmaci(schedaRicerca.getCognome(), "farmaco1")).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerNomeCognome(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setNome("Marco");
    schedaRicerca.setCognome("Rossi");

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Marco");
    scheda1.setCognome("Rossi");

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByNomeAndAndCognome(schedaRicerca.getNome(), schedaRicerca.getCognome())).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerNome(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setNome("Marco");

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Marco");
    scheda1.setCognome("Verdi");

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByNome(schedaRicerca.getNome())).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerCognome(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    schedaRicerca.setCodiceFiscale("");
    schedaRicerca.setCognome("Rossi");

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Michele");
    scheda1.setCognome("Rossi");

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByCognome(schedaRicerca.getCognome())).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }

  @Test
  void checkOttieniSchedaPazientePerFarmaco(){
    //given
    SchedaPaziente schedaRicerca = new SchedaPaziente();
    HashMap<String, Double> hashMap = new HashMap<>();
    hashMap.put("farmaco1", 100.00);
    hashMap.put("farmaco2", 200.00);
    schedaRicerca.setFarmaci(hashMap);

    SchedaPaziente scheda1 = new SchedaPaziente();
    scheda1.setCodiceFiscale("codiceFiscale1");
    scheda1.setNome("Michele");
    scheda1.setCognome("Rossi");
    scheda1.setFarmaci(hashMap);

    SchedaPaziente scheda2 = new SchedaPaziente();
    scheda2.setCodiceFiscale("codiceFiscale2");
    scheda2.setNome("Marco");
    scheda2.setCognome("Rossi");
    hashMap.remove("farmaco2");
    hashMap.put("farmaco3", 100.00);
    scheda2.setFarmaci(hashMap);

    List<SchedaPaziente> listPazientiExpected = new ArrayList<>();
    listPazientiExpected.add(scheda1);
    listPazientiExpected.add(scheda2);

    //when
    when(repository.findByFarmaco("farmaco1")).thenReturn(listPazientiExpected);

    //then
    assertEquals(listPazientiExpected, service.ottieniSchedePazientiByPaziente(schedaRicerca));
    System.out.println( service.ottieniSchedePazientiByPaziente(schedaRicerca));
  }


  @Test
  void checkOttieniSchedaPazientePerCodiceFiscale() {
    //given
    String cf = "RSSMRC75R13F839Q";

    SchedaPaziente schedaExpected = new SchedaPaziente();
    List<String> l = new ArrayList<>();
    HashMap<String, Double> hashMap = new HashMap<>();
    schedaExpected.setCodiceFiscale(cf);
    schedaExpected.setMalattie(l);
    schedaExpected.setFarmaci(hashMap);

    //when
    when(repository.findById(cf)).thenReturn(Optional.of(schedaExpected));

    //then
    assertEquals(schedaExpected, service.ottieniSchedaPazientePerCodiceFiscale(cf));
  }
}
