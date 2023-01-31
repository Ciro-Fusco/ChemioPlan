package com.example.prenotazione.repository;



import com.example.prenotazione.dto.PrenotazioneResponse;
import com.example.prenotazione.model.Prenotazione;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


/**
 * <p>Questa interfaccia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 *  * per la collezione Prenotazione del database.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface PrenotazioneRepository extends MongoRepository<Prenotazione, String> {

  @Query("{ 'data' : ?0 }")
  List<Prenotazione> findByData(Date data);
}
