package com.chemioplan.SchedaPaziente.repository;

import com.chemioplan.SchedaPaziente.model.SchedaPaziente;
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
}
