package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.ArchivioPazienti;
import com.example.apigateway.stub.model.Paziente;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/fhir/paziente")
public class PazienteController {
    private ArchivioPazienti pazienti = new ArchivioPazienti();

    @GetMapping
    public List<Paziente> getAll() throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
        }
        for (Paziente p : pazienti.getPazienti()){
            System.out.println(p.getDataNascita());
        }
        System.out.println();
        return pazienti.getPazienti();
    }

    @GetMapping("/{cf}")
    public Paziente getByCf(@PathVariable String cf) throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
        }
        return pazienti.findByCf(cf);
    }

    @GetMapping("/nome/{nome}")
    public List<Paziente> getByNome(@PathVariable String nome) throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
            return pazienti.findByNome(nome);
        }
        return pazienti.findByNome(nome);
    }

    @GetMapping("/cognome/{cognome}")
    public List<Paziente> getByCognome(@PathVariable String cognome) throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
            return pazienti.findByCognome(cognome);
        }
        return pazienti.findByCognome(cognome);
    }

    @GetMapping("/luogo-nascita/{luogo}")
    public List<Paziente> getByLuogo(@PathVariable String luogo) throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
            return pazienti.findByLuogoNascita(luogo);
        }
        return pazienti.findByLuogoNascita(luogo);
    }

    @GetMapping("/data/{data}")
    public List<Paziente> getByData(@PathVariable String data) throws ParseException {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
            return pazienti.findByDataNascita(data);
        }
        return pazienti.findByDataNascita(data);
    }

    @PostMapping("/nome-cognome")
    public List<Paziente> getByNomeCognome(@RequestBody Paziente paziente) throws ParseException {
        if (pazienti.getPazienti().isEmpty()) {
            pazienti.initialize();
        }

        return pazienti.findByNomeCognome(paziente.getNome(), paziente.getCognome());
    }

    @PostMapping("/nome-cognome-luogo")
    public List<Paziente> getByNomeCognomeLuogo(@RequestBody Paziente paziente) throws ParseException {
        if (pazienti.getPazienti().isEmpty()) {
            pazienti.initialize();
        }
        return pazienti.findByNomeCognomeLuogo(paziente.getNome(), paziente.getCognome(), paziente.getCittàNascita());
    }

    /*
    finire /nome-cognome-data
    @PostMapping("/nome-cognome-data")
    public List<Paziente> getByNomeCognomeData(@RequestBody Paziente paziente) throws ParseException {
        if (pazienti.getPazienti().isEmpty()) {
            pazienti.initialize();
        }
        System.out.println(paziente.toString());
        return null;//pazienti.findByNomeCognomeData(paziente.getNome(), paziente.getCognome(), paziente.getDataNascita());
    }

    fare /nome-cognome-luogo-data
     */
}
