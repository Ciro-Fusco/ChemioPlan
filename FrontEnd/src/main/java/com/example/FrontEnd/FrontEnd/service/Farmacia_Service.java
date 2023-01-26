package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Lotto;
import com.example.FrontEnd.FrontEnd.model.OrdineRequest;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Farmacia_Service implements IFarmacia_Service {
    private RestTemplate restTemplate = new RestTemplate();
    private String farmaciaResourceUrl = "http://localhost:8080/farmacia";

    @Override
    public SchedaFarmaco getFarmaco(String id) {
        SchedaFarmaco s = restTemplate.getForObject(farmaciaResourceUrl + "/" + id , SchedaFarmaco.class);
        return s;
    }

    @Override
    public SchedaFarmaco[] getAllFarmaci() {
        SchedaFarmaco[] s = restTemplate.getForObject(farmaciaResourceUrl , SchedaFarmaco[].class);
        return s;
    }

    @Override
    public String addFarmaco(SchedaFarmaco scheda) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SchedaFarmaco> entity = new HttpEntity<>(scheda, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.postForEntity(farmaciaResourceUrl, entity, String.class);
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
            response = restTemplate.postForEntity(farmaciaResourceUrl + "/nuovo-lotto/" + codice, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String modificaFarmaco(String codice, SchedaFarmaco schedaFarmaco) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SchedaFarmaco> entity = new HttpEntity<>(schedaFarmaco, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(farmaciaResourceUrl + "/" + codice, HttpMethod.PUT, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public Lotto getLotto(String codiceFarmaco, Integer numeroLotto) {
        Lotto l = restTemplate.getForObject(
                farmaciaResourceUrl + "/get-lotto/" + codiceFarmaco + "/" + numeroLotto,
                Lotto.class
        );
        return l;
    }

    @Override
    public String modificaLotto(String codiceFarmaco, Lotto lotto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Lotto> entity = new HttpEntity<>(lotto, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(farmaciaResourceUrl + "/modifica-lotto/" + codiceFarmaco, HttpMethod.PUT, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String nuovoOrdine(OrdineRequest ordine) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<OrdineRequest> entity = new HttpEntity<>(ordine, headers);
        try {
            return restTemplate.postForEntity(farmaciaResourceUrl + "/nuovo-ordine", entity, String.class).getBody();
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
