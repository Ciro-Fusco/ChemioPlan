package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Paziente;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class PazienteStub implements IPazienteStub{
    private RestTemplate restTemplate = new RestTemplate();

    private String url = "http://localhost:8080/fhir/paziente";

    @Override
    public Paziente findByCf(String cf) {
        return restTemplate.getForObject(url +"/" + cf, Paziente.class);
    }

    @Override
    public List<Paziente> findPazienti(Paziente p) {
        return restTemplate.postForObject(url + "/trova-paziente", p, List.class );
    }
}
