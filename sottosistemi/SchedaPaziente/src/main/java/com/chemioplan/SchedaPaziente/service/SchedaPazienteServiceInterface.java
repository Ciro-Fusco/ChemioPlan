package com.chemioplan.SchedaPaziente.service;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import java.util.List;

/**
 * <p> Questa interfaccia fornisce le funzionalità per il controoller</p>
 */

public interface SchedaPazienteServiceInterface {
    /**
     * <p> Questo metodo restituisce tutte le schedePaziente presenti nel DB</p>
     *
     */
    List<SchedaPaziente> ottieniSchedePazienti();

    /**
     * <p> Questo metodo restituisce una schedaPaziente con un determinato ID</p>
     * @param id l'id della schedaPaziente da cercare
     * @return la schedaPaziente trovata
     */
    SchedaPaziente ottieniSchedaPazientePerId(int id);

    /**
     * <p> Questo metodo restituisce una schedaPaziente con un determinato codiceFiscale</p>
     * @param CodiceFiscale il CodiceFiscale della schedaPaziente da cercare
     * @return la schedaPaziente trovata
     */
    SchedaPaziente ottieniSchedaPazientePerCodiceFiscale(String CodiceFiscale);

    /**
     * <p> Questo metodo inserisce una nuova schedaPaziente nel DB</p>
     * @param schedaPaziente la schedaPaziente da inserire
     */
    void aggiungiSchedaPaziente(SchedaPaziente schedaPaziente);

    /**
     * <p>Questo metodo modifica una schedaPaziente presente nel DB</p>
     * @param id l'id della schedaPaziente da modificare
     * @param schedaPaziente la schedaPaziente con le modifiche
     */
    void modificaSchedaPaziente(int id, SchedaPaziente schedaPaziente);

    /**
     *<p> Questo metodo elimina una schedaPaziente presente nel DB</p>
     * @param id l'id della schedaPaziente da eliminare
     */
    void eliminaSchedaPaziente(int id);



}
