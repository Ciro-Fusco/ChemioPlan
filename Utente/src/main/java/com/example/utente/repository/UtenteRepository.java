package com.example.utente.repository;

import com.example.utente.model.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


/**
 * <p>Questa interfaccia ci permette  di eseguire operazioni con il Database.</p>
 *
 * @author chris
 * @version 1.0
 */
public interface UtenteRepository extends MongoRepository<Utente, Integer> {
  /**
   * <p>Queste metodo ci permette di trovare un utente nel Database in base al suo username.</p>
   *
   * @param user username dell'utente da trovare
   * @return Utente con uno specifico usernare
   * @since 1.0
   */
  @Query("{'credenziali.user' :  ?0}")
  Utente findByUser(String user);
}
