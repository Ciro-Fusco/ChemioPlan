package com.example.apigateway.stub.controller;

import com.example.apigateway.stub.archivio.MagazzinoFarmaco;
import com.example.apigateway.stub.model.Farmaco;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/fhir/farmaci")
public class FarmacoController {

    private MagazzinoFarmaco farmaci = new MagazzinoFarmaco();

    @GetMapping
    public List<Farmaco> getAll() {
        if (farmaci.getFarmaci().isEmpty()){
            farmaci.initialize();
        }
        return farmaci.getFarmaci();
    }

    @GetMapping("/{id}")
    public Farmaco getById(@PathVariable String id) {
        if (farmaci.getFarmaci().isEmpty()){
            farmaci.initialize();
        }
        return farmaci.findById(id);
    }
}
