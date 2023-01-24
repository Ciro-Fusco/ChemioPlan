package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class Utente_Service implements IUtente_Service {

    private RestTemplate restTemplate = new RestTemplate();
    private String UtenteResourceUrl = "http://localhost:8080/utente";

    @Override
    public Utente getUtente(Integer id) {
        Utente u = restTemplate.getForObject(UtenteResourceUrl+"/"+id , Utente.class);
        return u;
    }

    @Override
    public Utente[] getUtenti() {
        Utente[] u = restTemplate.getForObject(UtenteResourceUrl, Utente[].class);
        return u;
    }

}
