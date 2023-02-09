package com.chemioplan.SchedaPaziente.repository;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


/**
 * <p>Questa interfaciia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 * per la collezzione pazienti nel databse.</p>
 *
 * @author vittorio contardo
 * @version 0.1
 */
public interface SchedaPazienteRepository extends MongoRepository<SchedaPaziente, String> {
  List<SchedaPaziente> findByNome(String nome);
  List<SchedaPaziente> findByCognome(String cognome);
  List<SchedaPaziente> findByNomeAndAndCognome(String nome, String cognome);

  @Query("{'farmaci.?0' : {$exists : true}}")
  List<SchedaPaziente> findByFarmaco(String codiceFarmaco);

  @Query("{nome : ?0, cognome : ?1 ,'farmaci.?2' : {$exists : true}}")
  List<SchedaPaziente> findByNomeAndCognomeAndFarmaci(String nome, String cognome, String codiceFarmaco);

  @Query("{nome : ?0, 'farmaci.?1' : {$exists : true}}")
  List<SchedaPaziente> findByNomeAndFarmaci(String nome, String codiceFarmaco);

  @Query("{cognome : ?0 ,'farmaci.?1' : {$exists : true}}")
  List<SchedaPaziente> findByCognomeAndFarmaci(String cognome, String codiceFarmaco);
}
