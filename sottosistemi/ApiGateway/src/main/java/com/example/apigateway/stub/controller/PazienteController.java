package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.ArchivioPazienti;
import com.example.apigateway.stub.model.Paziente;
import com.fasterxml.jackson.databind.util.JSONPObject;
import io.netty.handler.codec.json.JsonObjectDecoder;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fhir/paziente")
public class PazienteController {
    private ArchivioPazienti pazienti = new ArchivioPazienti();

    @GetMapping
    public List<Paziente> getAll()  {
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
    public Paziente getByCf(@PathVariable String cf)  {
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
        }
        return pazienti.findByCf(cf);
    }

    @PostMapping("/trova-paziente")
    public List<Paziente> findPaziente(@RequestBody Paziente p){
        if (pazienti.getPazienti().isEmpty()){
            pazienti.initialize();
        }
        System.out.println(p.toString());
        List<Paziente> paz = new ArrayList<>();

        if(p.getNome() != null && p.getCognome() != null && p.getDataNascita() != null && p.getLuogoNascita() != null){
            paz = pazienti.findByNomeCognomeDataLuogo(p.getNome(), p.getCognome(), p.getDataNascita(),p.getLuogoNascita());
        }
        if(p.getNome() != null && p.getCognome() != null && p.getDataNascita() != null){
            paz = pazienti.findByNomeCognomeData(p.getNome(), p.getCognome(), p.getDataNascita());
        }
        if(p.getNome() != null && p.getCognome() != null && p.getLuogoNascita() != null){
            paz = pazienti.findByNomeCognomeData(p.getNome(), p.getCognome(), p.getLuogoNascita());
        }
        if (p.getNome() != null && p.getCognome() != null){
            System.out.println("qui ok");
            paz = pazienti.findByNomeCognome(p.getNome(), p.getCognome());
        }

        if(!p.getCodiceFiscale().equals("")){
            paz.add(pazienti.findByCf(p.getCodiceFiscale()));
        }

        System.out.println(paz);
        return paz;
    }
}
