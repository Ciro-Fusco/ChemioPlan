package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.ArchivioMalattia;
import com.example.apigateway.stub.model.Malattia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fhir/malattia")
public class MalattiaController {
    ArchivioMalattia malattie = new ArchivioMalattia();

    @GetMapping
    public List<Malattia> getAll(){
        if(malattie.getMalattie().isEmpty()){
            malattie.initialize();
        }
        return malattie.getMalattie();
    }

    @GetMapping("/codice/{codice}")
    public Malattia getByCodice(@PathVariable String codice){
        if(malattie.getMalattie().isEmpty()){
            malattie.initialize();
        }
        return malattie.findByCodice(codice);
    }

    @GetMapping("/nome/{nome}")
    public List<Malattia> getByNome(@PathVariable String nome){
        if(malattie.getMalattie().isEmpty()){
            malattie.initialize();
        }
        return malattie.findByNome(nome);
    }

    @GetMapping("/parteCorpo/{parteCorpo}")
    public List<Malattia> getByParteCorpo(@PathVariable String parteCorpo){
        if(malattie.getMalattie().isEmpty()){
            malattie.initialize();
        }
        return malattie.findByParteCorpo(parteCorpo);
    }
}
