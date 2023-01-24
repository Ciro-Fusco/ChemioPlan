package com.example.FrontEnd.FrontEnd.service;

import com.example.FrontEnd.FrontEnd.model.SchedaFarmaco;
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

}
