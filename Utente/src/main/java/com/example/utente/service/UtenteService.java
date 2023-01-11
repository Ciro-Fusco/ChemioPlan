package com.example.utente.service;

import com.example.utente.exception.CredenzialiNonValide;
import com.example.utente.exception.UtenteAlreadyExist;
import com.example.utente.exception.UtenteNotFoundException;
import com.example.utente.model.Utente;
import com.example.utente.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService implements IUtenteService{
    @Autowired
    private UtenteRepository repository;

    @Override
    public List<Utente> getAllUtenti() {
        return repository.findAll();
    }

    @Override
    public Utente getUtenteById(Integer id) {
        return repository.findById(id).orElseThrow(()-> new UtenteNotFoundException("Utente con codice " + id + "non trovato"));

    }

    @Override
    public void deleteUtente(Integer id) {
        var obj = repository.findById(id);

        if (obj.isEmpty()){
            new UtenteNotFoundException("Utente da rimuovere con codice " + id + " non trovato");
        }
        repository.deleteById(id);
    }

    @Override
    public void updateUtente(Integer id, Utente u) {
        if (repository.findById(id).isEmpty())
            throw new UtenteNotFoundException("Utente da modificare con codice " + id + " non trovato");

        repository.save(u);
    }

    @Override
    public void addUtente(Utente u) {
        if (repository.existsById(u.getId())){
            throw new UtenteAlreadyExist("Utente con codice " + u.getId() + " già esistente");
        }

        if (repository.findByUser(u.getCredenziali().getUser()) != null) {
            throw new CredenzialiNonValide("Username esistente");
        }

        repository.save(u);
    }

    @Override
    public void verificaCredenziali(String user, String pass) {
        Utente u = repository.findByUser(user);
        if (u == null) {
            throw new CredenzialiNonValide("User Errato");
        }

        if(!u.getCredenziali().getPass().equals(pass))
            throw new CredenzialiNonValide("Credenziali non valide");
    }
}
