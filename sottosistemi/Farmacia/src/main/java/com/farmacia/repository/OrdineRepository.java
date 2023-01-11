package com.farmacia.repository;

import com.farmacia.model.Ordine;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>Questa interfaccia fornisce tutte le funzionalità dell'interfaccia MongoRepository
 * per la collezione Ordine del database.</p>
 *
 * @author Francesco Pio di Pippa
 * @version 0.1
 */
public interface OrdineRepository extends MongoRepository<Ordine, String> {}