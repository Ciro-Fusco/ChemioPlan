package com.chemioplan.SchedaPaziente.repository;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

/**
 *<p>Questa interfaccia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 * per la collezione pazienti del DB</p>
 */
public interface SchedaPazienteRepository extends MongoRepository<SchedaPaziente, Integer> {


     SchedaPaziente findByCodiceFiscale(String CodiceFiscale);


}
