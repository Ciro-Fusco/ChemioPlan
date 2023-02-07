package com.example.FrontEnd.FrontEnd.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = { "/mlservices" })
public class mlController {

    @GetMapping(value = { "/ottimizzazioneacquisto" })
    public String ottimizzazioneacquisto(@RequestParam("codice") String codice) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/ottimizzazioneacquisti?codice=" + codice;
        String result = restTemplate.getForObject(url, String.class);
        if (result != null) {
            result = "Stima di acquisto: " + result + " flaconi";
        } else {
            result = "";
        }
        System.out.println(result);
        return result;

    }
}
