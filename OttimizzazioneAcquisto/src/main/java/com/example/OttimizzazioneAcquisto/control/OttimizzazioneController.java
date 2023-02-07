package com.example.OttimizzazioneAcquisto.control;

import com.example.OttimizzazioneAcquisto.service.IOttimizzazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * La classe OttimizzazioneController responsabile dell'elaborazione delle
 * richieste di Ottimizzazione delle quantita da acquistare.
 * </p>
 *
 * @author Francesco Matteis
 * @version 1.0 (07/02/2023)
 */
@RestController
@RequestMapping("ottimizzazioneacquisti")
public class OttimizzazioneController {
    /**
     * <p>
     * Riferimento alla classe service.
     * </p>
     */
    @Autowired
    private IOttimizzazioneService service;

    /**
     * <p>
     * Questo metodo restituisce la quantità di flaconi da acquistare
     * </p>
     *
     * @param codice codice del farmaco da acquistare
     * @return numeri di flaconi da acquistare
     */
    @GetMapping()
    public String Ottimizzaacquisto(@RequestParam("codice") String codice) {

        return service.Ottimizza(codice);

    }
}
