package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.Credenziali;
import com.example.FrontEnd.FrontEnd.model.Utente;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UtenteService implements IUtenteService {

    private RestTemplate restTemplate = new RestTemplate();
    private String utenteResourceUrl = "http://localhost:8080/utente";

    @Override
    public Utente getUtente(Integer id) {
        Utente u = restTemplate.getForObject(utenteResourceUrl+"/"+id , Utente.class);
        return u;
    }

    @Override
    public Utente[] getUtenti() {
        Utente[] u = restTemplate.getForObject(utenteResourceUrl, Utente[].class);
        return u;
    }

    @Override
    public String verificaCrendenziali(Credenziali credenziali) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Credenziali> entity = new HttpEntity<>(credenziali, headers);
        ResponseEntity<String> response = null;

        try{
            response = restTemplate.postForEntity(utenteResourceUrl + "/login", entity, String.class);
            System.out.println(response.getBody());
            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String getRuoloByUser(String user) {
        return restTemplate.postForObject(utenteResourceUrl +"/ruolo" , user, String.class);
    }

    @Override
    public String aggiungiUtente(Utente utente) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Utente> entity = new HttpEntity<>(utente, headers);
        ResponseEntity<String> response = null;

        try{
            response = restTemplate.postForEntity(utenteResourceUrl, entity, String.class);
            System.out.println(response.getBody());
            return response.getBody();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String modificaUtente(Integer id, Utente utente) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Utente> entity = new HttpEntity<>(utente, headers);
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(utenteResourceUrl + "/" + id, HttpMethod.PUT, entity, String.class);
            return response.getBody();
        } catch (Exception e){
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String elimina(Integer id) {
        try {
            restTemplate.delete(utenteResourceUrl + "/" + id);
            return "Utente " + id + " eliminato";
        } catch (Exception e){
            return e.getMessage();
        }
    }
}
