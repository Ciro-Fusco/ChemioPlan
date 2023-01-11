package com.example.utente.repository;

import com.example.utente.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface UtenteRepository extends MongoRepository<Utente, Integer> {
    @Query("{'credenziali.user' :  ?0}")
    Utente findByUser(String user);
}
