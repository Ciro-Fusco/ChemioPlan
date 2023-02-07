package com.example.OttimizzazioneAcquisto.control;

import com.example.OttimizzazioneAcquisto.service.IOttimizzazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ottimizzazioneacquisti")
public class OttimizzazioneController {

    @Autowired
    private IOttimizzazioneService service;

    @GetMapping()
    public String Ottimizzaacquisto(@RequestParam("codice") String codice) {

        return service.Ottimizza(codice);

    }
}
