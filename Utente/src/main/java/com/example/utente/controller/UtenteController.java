package com.example.utente.controller;


import com.example.utente.model.Credenziali;
import com.example.utente.model.Utente;
import com.example.utente.service.IUtenteService;
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
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("utente")
public class UtenteController {
    @Autowired
    private IUtenteService service;

    @GetMapping
    public List<Utente> ottieniUtenti() {
        return service.getAllUtenti();
    }

    @GetMapping("/{id}")
    public Utente ottieneUtentePerId(@PathVariable Integer id) {
        return service.getUtenteById(id);
    }

    @PostMapping
    public ResponseEntity<?> aggiungiUtente(@RequestBody Utente u) {
        service.addUtente(u);
        return ResponseEntity.ok("Utente salvato con codice : " + u.getId());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> rimuoviUtente(@PathVariable Integer id) {
        service.deleteUtente(id);
        return ResponseEntity.ok("Utente rimosso correttamente con codice : " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificaUtente(@PathVariable Integer id, @RequestBody Utente u) {
        service.updateUtente(id, u);
        return ResponseEntity.ok("Utente con codice : " + id + " modificato correttamente");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credenziali credenziali) {
        service.verificaCredenziali(credenziali.getUser(), credenziali.getPass());
        return ResponseEntity.ok("Login effettuato con succeso!");
    }
}
