package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Farmacia_Service implements IFarmacia_Service {
    private RestTemplate restTemplate = new RestTemplate();
    private String UtenteResourceUrl = "http://localhost:8080/farmacia";

    @Override
    public SchedaFarmaco getFarmaco(String id) {
        SchedaFarmaco s = restTemplate.getForObject(UtenteResourceUrl+"/"+id , SchedaFarmaco.class);
        return s;
    }

    @Override
    public SchedaFarmaco[] getAllFarmaci() {
        SchedaFarmaco[] s = restTemplate.getForObject(UtenteResourceUrl , SchedaFarmaco[].class);
        return s;
    }

    @Override
    public String addFarmaco(SchedaFarmaco scheda) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SchedaFarmaco> entity = new HttpEntity<>(scheda, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity(UtenteResourceUrl, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String nuovoLotto(String codice, Lotto lotto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Lotto> entity = new HttpEntity<>(lotto, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity(UtenteResourceUrl + "/nuovo-lotto/" + codice, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
