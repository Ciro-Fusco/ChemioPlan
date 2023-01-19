package com.chemioplan.SchedaPaziente.service;

import com.chemioplan.SchedaPaziente.exception.SchedaPazienteAlredyExistException;
import com.chemioplan.SchedaPaziente.exception.SchedaPazienteNotFoundException;
import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import com.chemioplan.SchedaPaziente.repository.SchedaPazienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
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
     * <p> Questo metodo restituisce una schedaPaziente con un determinato ID</p>
     * @param Id l'id della schedaPaziente da cercare
     * @return la schedaPaziente trovata
     */
    @Override
    public SchedaPaziente ottieniSchedaPazientePerId(int Id) {
        return schedaPazienteRepository.findById(Id).orElseThrow(()->new SchedaPazienteNotFoundException("SchedaPaziente con id "+Id+" non trovata"));
    }

    /**
     * <p> Questo metodo restituisce una schedaPaziente con un determinato codiceFiscale</p>
     * @param CodiceFiscale il CodiceFiscale della schedaPaziente da cercare
     * @return la schedaPaziente trovata
     */
    @Override
    public SchedaPaziente ottieniSchedaPazientePerCodiceFiscale(String CodiceFiscale) {

        SchedaPaziente scheda = schedaPazienteRepository.findByCodiceFiscale(CodiceFiscale);
            if(scheda == null){
               throw  new SchedaPazienteNotFoundException("SchedaPaziente con codiceFiscale "+CodiceFiscale+" non trovata");
            }
            return scheda;
    }

    /**
     * <p> Questo metodo inserisce una nuova schedaPaziente nel DB</p>
     * @param schedaPaziente la schedaPaziente da inserire
     */
    @Override
    public void aggiungiSchedaPaziente(SchedaPaziente schedaPaziente) {
        if(schedaPazienteRepository.existsById(schedaPaziente.getId())) {
            throw new SchedaPazienteAlredyExistException("SchedaPaziente con id " + schedaPaziente.getId() + " non trovata");
        }
        schedaPazienteRepository.save(schedaPaziente);

    }

    /**
     * <p>Questo metodo modifica una schedaPaziente presente nel DB</p>
     * @param Id l'id della schedaPaziente da modificare
     * @param schedaPaziente la schedaPaziente con le modifiche
     */
    @Override
    public void modificaSchedaPaziente(int Id, SchedaPaziente schedaPaziente) {
        var optional = schedaPazienteRepository.findById(Id);

        if(optional.isEmpty()){
            throw new SchedaPazienteNotFoundException("SchedaPaziente con id"+ Id+ "non trovata");
        }
        var scheda =optional.get();
        if(schedaPaziente.getCodiceFiscale() != null){
            scheda.setCodiceFiscale(schedaPaziente.getCodiceFiscale());
        }
    }

    /**
     *<p> Questo metodo elimina una schedaPaziente presente nel DB</p>
     * @param Id l'id della schedaPaziente da eliminare
     */
    @Override
    public void eliminaSchedaPaziente(int Id) {
        var optional = schedaPazienteRepository.findById(Id);

        if(optional.isEmpty()){
            throw new SchedaPazienteNotFoundException("Scheda Paziente con ID: " +Id + "non torvato ");
        }
        schedaPazienteRepository.deleteById(Id);

    }
}
