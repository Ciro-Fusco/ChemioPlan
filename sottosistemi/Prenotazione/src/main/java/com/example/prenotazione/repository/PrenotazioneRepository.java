package com.example.prenotazione.repository;


import com.example.prenotazione.dto.PrenotazioneResponse;
import com.example.prenotazione.model.Prenotazione;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * <p>Questa interfaccia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 *  * per la collezione Prenotazione del database.</p>
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
public interface PrenotazioneRepository extends MongoRepository<Prenotazione, String> {

  @Query(value = "{ 'sala': ?0}")
  List<Prenotazione> findBySala(String sala);

  @Query(value = "{ 'data' :?0}")
  List<Prenotazione> findByData(String data);

  @Query(value = "{ 'poltrona' :?0}")
  List<Prenotazione> findByPoltrona(String poltrona);
}
