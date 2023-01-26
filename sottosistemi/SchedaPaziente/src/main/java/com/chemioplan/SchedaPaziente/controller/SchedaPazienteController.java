package com.chemioplan.SchedaPaziente.controller;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import com.chemioplan.SchedaPaziente.service.SchedaPazienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> Questa classe SchedaPazienteController è responsabile dell'elaborazione delle richieste in entrata</p>
 */

@RestController
@RequestMapping("pazienti")
public class SchedaPazienteController {
    /**
     * <p> riferimento alla classe service</p>
     */
    @Autowired
    private SchedaPazienteService schedaPazienteService;

    /**
     * <p> Questo metodo restituisce tutte le SchedePazienti presenti nel DB con una chiamata GET</p>
     * @return
     */
    @GetMapping
    public List<SchedaPaziente> ottieniSchedePazienti(){
        return schedaPazienteService.ottieniSchedePazienti();
    }


    /**
     * Questo metodo , con chiamata Get, restituisce una determinata scheda paziente
     * con un codiceFiscale presente nell'url
     * @Return la schedaPaziente con il codiceFiscale dato in input
     */

    @GetMapping("/{CodiceFiscale}")
    public SchedaPaziente ottieniSchedaPaziente(@PathVariable String CodiceFiscale){
        return schedaPazienteService.ottieniSchedaPazientePerCodiceFiscale(CodiceFiscale);
    }

    /**
     * <p>Questo metodo inserisce una nuova SchedaPaziente nel DB</p>
     * @param schedaPaziente la scheda che viene passata nel body della richiesta
     * @return una ResponseEntity con un messaggio e lo status HTTP
     */
    @PostMapping
    public ResponseEntity<?> aggiungiSchedaPaziente(@RequestBody SchedaPaziente schedaPaziente){
        schedaPazienteService.aggiungiSchedaPaziente(schedaPaziente);
        return new ResponseEntity<String>("SchedaPaziente salvata "+ schedaPaziente.getCodiceFiscale(), HttpStatus.CREATED);
    }

    /**
     * <p>Questo metodo modifica una schedaPaziente presente nel DB </p>
     * @param codiceFiscale il codiceFiscale della schedaPaziente passato nell'url
     * @param schedaPaziente la schedaPaziente con le modifiche passata nel body della richiesta
     * @return una ResponseEntity con un messaggio e lo status HTTP
     */
    @PutMapping("/{codiceFiscale}")
    public ResponseEntity<?> modificaSchedaPaziente(@PathVariable String codiceFiscale, @RequestBody SchedaPaziente schedaPaziente){
        schedaPazienteService.modificaSchedaPaziente(codiceFiscale, schedaPaziente);
        return ResponseEntity.ok("SchedaPaziente modificata correttamente" +schedaPaziente.getCodiceFiscale());
    }

    /**
     * <p>Questo metodo elimina la schedaPaziente con un determinato codiceFiscaòe</p>
     *
     * @param codiceFiscale il codiceFiscale della schedaPaziente da eliminare
     * @return una ResponseEntity con un messaggio
     */
    @DeleteMapping("/{codiceFiscale}")
    public ResponseEntity<?> eliminaSchedaPaziente(@PathVariable String codiceFiscale){
        schedaPazienteService.eliminaSchedaPaziente(codiceFiscale);
        return ResponseEntity.ok("Scheda farmaco eliminata correttamente" +codiceFiscale);
    }


}
