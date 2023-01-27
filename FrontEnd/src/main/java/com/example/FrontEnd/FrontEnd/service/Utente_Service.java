package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.GenericDeclaration;
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

    @Override
    public String verificaCrendenziali(Credenziali credenziali) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Credenziali> entity = new HttpEntity<>(credenziali, headers);
        ResponseEntity<String> response = null;

        try{
            response = restTemplate.postForEntity(UtenteResourceUrl + "/login", entity, String.class);
            System.out.println(response.getBody());
            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }


    }

    @Override
    public String getRuoloByUser(String user) {
        return restTemplate.postForObject(UtenteResourceUrl +"/ruolo" , user, String.class);
    }

}
