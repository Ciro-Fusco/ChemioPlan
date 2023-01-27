package com.example.prenotazione.repository;



import com.example.prenotazione.model.Prenotazione;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * <p>Questa interfaccia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 *  * per la collezione Prenotazione del database.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface PrenotazioneRepository extends MongoRepository<Prenotazione, String> {

}
