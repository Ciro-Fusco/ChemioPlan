package com.chemioplan.SchedaPaziente.service;

import com.chemioplan.SchedaPaziente.exception.SchedaPazienteAlredyExistException;
import com.chemioplan.SchedaPaziente.exception.SchedaPazienteNotFoundException;
import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import com.chemioplan.SchedaPaziente.repository.SchedaPazienteRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>Questa classe implementa l'interfaccia SchedaPazienteServieInterface</p>
 */

@Service

public class SchedaPazienteService implements SchedaPazienteServiceInterface{

    /**
     * <p>Riferimento alla repository SchedaPazienteRepository</p>
     */
    @Autowired
    private SchedaPazienteRepository schedaPazienteRepository;

    /**
     * <p>Questo metodo restituisce tutti le schedePazienti presenti nel database.</p>
     *
     * @return lista di SchedaPazienti
     */
    @Override
    public  List<SchedaPaziente> ottieniSchedePazienti() {
        return schedaPazienteRepository.findAll();
    }


    /**
     * <p> Questo metodo restituisce una schedaPaziente con un determinato codiceFiscale</p>
     * @param codiceFiscale il CodiceFiscale della schedaPaziente da cercare
     * @return la schedaPaziente trovata
     */
    @Override
    public SchedaPaziente ottieniSchedaPazientePerCodiceFiscale(String codiceFiscale) {
        var optional = schedaPazienteRepository.findById(codiceFiscale);
        if(optional.isEmpty()){
           throw  new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale " + codiceFiscale + " non trovata");
        }
        return optional.get();
    }

    /**
     * <p> Questo metodo inserisce una nuova schedaPaziente nel DB</p>
     * @param schedaPaziente la schedaPaziente da inserire
     */
    @Override
    public void aggiungiSchedaPaziente(SchedaPaziente schedaPaziente) {
        if(schedaPazienteRepository.existsById(schedaPaziente.getCodiceFiscale())){
            throw new SchedaPazienteAlredyExistException("Scheda Paziente con codiceFiscale: |"
            + schedaPaziente.getCodiceFiscale() + " | già esistente" );
        }
        schedaPazienteRepository.save(schedaPaziente);
    }

    /**
     * <p>Questo metodo modifica una schedaPaziente presente nel DB</p>
     * @param codiceFiscale il codiceFiscale della schedaPaziente da modificare
     * @param schedaPaziente la schedaPaziente con le modifiche
     */
    @Override
    public void modificaSchedaPaziente(String codiceFiscale, SchedaPaziente schedaPaziente) {
        var optional = schedaPazienteRepository.findById(codiceFiscale);
        if (optional.isEmpty())
            throw new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale " + codiceFiscale + " non trovata");

        var scheda = optional.get();
        if (schedaPaziente.getFarmaci() != null)
            scheda.setFarmaci(schedaPaziente.getFarmaci());
        if (schedaPaziente.getMalattie() != null)
            scheda.setMalattie(schedaPaziente.getMalattie());
        schedaPazienteRepository.save(scheda);
    }

    /**
     *<p> Questo metodo elimina una schedaPaziente presente nel DB</p>
     * @param codiceFiscale il codiceFiscale della schedaPaziente da eliminare
     */
    @Override
    public void eliminaSchedaPaziente(String codiceFiscale) {
        var optional = schedaPazienteRepository.findById(codiceFiscale);
        if(optional.isEmpty()){
            throw  new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale " + codiceFiscale + " non trovata");
        }
        schedaPazienteRepository.deleteById(codiceFiscale);


    }

    @Override
    public HashMap<String,Double> ottieniFarmaciPerCodiceFiscale(String codiceFiscale) {
        var optional = schedaPazienteRepository.findById(codiceFiscale);
        if(optional.isEmpty()){
            throw  new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale " + codiceFiscale + " non trovata");
        }
        return optional.get().getFarmaci();
    }
}
