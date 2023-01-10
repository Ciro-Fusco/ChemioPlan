package com.farmacia.repository;

import com.farmacia.model.Ordine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdineRepository extends MongoRepository<Ordine, String> {}