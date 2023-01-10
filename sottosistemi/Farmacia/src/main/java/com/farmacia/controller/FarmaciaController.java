package com.farmacia.controller;

import com.farmacia.dto.OrdineRequest;
import com.farmacia.model.Lotto;
import com.farmacia.model.Ordine;
import com.farmacia.model.SchedaFarmaco;
import com.farmacia.service.FarmaciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("farmacia")
public class FarmaciaController {
  @Autowired
  private FarmaciaService service;

  @GetMapping
  public List<SchedaFarmaco> ottieniFarmaci() {
    return service.ottieniFarmaci();
  }

  @GetMapping("/{codice}")
  public SchedaFarmaco ottieniFarmaco(@PathVariable String codice) {
    return service.ottieniFarmacoPerCodice(codice);
  }

  @PostMapping
  public ResponseEntity<?> aggiungiFarmaco(@RequestBody SchedaFarmaco schedaFarmaco) {
    service.aggiungiFarmaco(schedaFarmaco);
    return new ResponseEntity<String>("Scheda Farmaco salvata " + schedaFarmaco.getCodice(), HttpStatus.CREATED);
  }

  @PutMapping("/{codice}")
  public ResponseEntity<?> modificaFarmaco(@PathVariable String codice, @RequestBody SchedaFarmaco schedaFarmaco) {
    service.modificaFarmaco(codice, schedaFarmaco);
    return ResponseEntity.ok("Scheda Farmaco modificata correttamente" + schedaFarmaco.getCodice());
  }

  @DeleteMapping("/{codice}")
  public ResponseEntity<?> eliminaFarmaco(@PathVariable String codice) {
    service.eliminaFarmaco(codice);
    return ResponseEntity.ok("Scheda Farmaco modificata correttamente" + codice);
  }

  @PostMapping("/nuovo-lotto")
  public ResponseEntity<?> nuovoLotto(@RequestBody Lotto lotto) {
    service.nuovoLotto(lotto);
    return ResponseEntity.ok("Nuovo lotto modificato correttamente sulla Scheda Farmaco " + lotto.getCodiceFarmaco());
  }

  @PostMapping("/nuovo-ordine")
  public ResponseEntity<?> nuovoOrdine(@RequestBody OrdineRequest ordine) {
    Ordine o = service.nuovoOrdine(ordine);
    return ResponseEntity.ok("Nuovo ordine effettuato correttamente ID " + o.getId());
  }

  @GetMapping("/ordine")
  public Ordine ottieniOrdine(@RequestParam String id) {
    return service.ottieniOrdine(id);
  }

  @GetMapping("ordine-consegnato")
  public ResponseEntity<?> ordineConsegnato(@RequestParam String id) {
    service.ordineConsegnato(id);
    return ResponseEntity.ok("Ordine " + id + " consegnato");
  }
}
