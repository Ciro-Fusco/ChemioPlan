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
     * con un id presente nell'url
     * @Return la schedaPaziente con l'id dato in input
     */

    @GetMapping("/{id}")
    public SchedaPaziente ottieniSchedaPaziente(@PathVariable int id){
        return schedaPazienteService.ottieniSchedaPazientePerId(id);
    }

    /**
     * Questo metodo , con chiamata Get, restituisce una determinata scheda paziente
     * con un codiceFiscale presente nell'url
     * @Return la schedaPaziente con il codiceFiscale dato in input
     */

    @GetMapping("/by-cf/{CodiceFiscale}")
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
        return new ResponseEntity<String>("SchedaPaziente salvata "+ schedaPaziente.getId(), HttpStatus.CREATED);
    }

    /**
     * <p>Questo metodo modifica una schedaPaziente presente nel DB con uno specifico ID</p>
     * @param Id l'id della schedaPaziente passato nell'url
     * @param schedaPaziente la schedaPaziente con le modifiche passata nel body della richiesta
     * @return una ResponseEntity con un messaggio e lo status HTTP
     */
    @PutMapping("/{Id}")
    public ResponseEntity<?> modificaSchedaPaziente(@PathVariable int Id, @RequestBody SchedaPaziente schedaPaziente){
        schedaPazienteService.modificaSchedaPaziente(Id, schedaPaziente);
        return ResponseEntity.ok("SchedaPaziente modificata correttamente" +schedaPaziente.getId());
    }

    /**
     * <p>Questo metodo elimina la schedaPaziente con un determinato Id</p>
     *
     * @param Id l'ID della schedaPaziente da eliminare
     * @return una ResponseEntity con un messaggio
     */
    @DeleteMapping("/{Id}")
    public ResponseEntity<?> eliminaSchedaPaziente(@PathVariable int Id){
        schedaPazienteService.eliminaSchedaPaziente(Id);
        return ResponseEntity.ok("Scheda farmaco eliminata correttamente" +Id);
    }


}
