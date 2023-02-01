package com.example.apigateway.stub.archivio;

import com.example.apigateway.stub.model.Indirizzo;
import com.example.apigateway.stub.model.Paziente;
import lombok.Data;

import java.text.*;
import java.util.*;


@Data
public class ArchivioPazienti {
    List<Paziente> pazienti = new ArrayList<>();

    public void initialize() {
        pazienti.add(new Paziente("RSSMRC75R13F839Q", "Marco", "Rossi", "13-10-1975" , new Indirizzo("Via Roma 10", "Napoli", "Italia", "20159"),"Napoli"));
        pazienti.add(new Paziente("CPAFNC98E05F141E", "Franco", "Capo", "05-05-1998", new Indirizzo("Piazza Rossi 5", "Mercogliano", "Italia", "45035"),"Avellino"));
        pazienti.add(new Paziente("BNCLSS90B49F839X", "Alessia", "Bianco", "09-02-1990", new Indirizzo("Via Napoli 451", "Napoli", "Italia", "20159"),"Napoli"));
        pazienti.add(new Paziente("RSSMRA00R21A783X", "Mario", "Rossi", "21-10-2000" ,new Indirizzo("Via Roma 45", "Benevento", "Italia", "45789"),"Benevento"));
    }

    public Paziente findByCf(String cf){
        List<String> codici = pazienti.stream().map((p)->p.getCodiceFiscale()).toList();
        return pazienti.get(codici.indexOf(cf));
    }

    public List<Paziente> findByNome(String nome){
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNome = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome)){
                System.out.println(p.toString());
                pazientiNome.add(p);
            }
        }
        return pazientiNome;
    }

    public List<Paziente> findByCognome(String cognome){
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiCognome = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getCognome().equals(cognome)){
                pazientiCognome.add(p);
            }
        }
        return pazientiCognome;
    }

    public List<Paziente> findByLuogoNascita(String luogo) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiLuogoNascita = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getLuogoNascita().equals(luogo)){
                pazientiLuogoNascita.add(p);
            }
        }
        return pazientiLuogoNascita;
    }

    public List<Paziente> findByDataNascita(String data)  {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiDataNascita = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getDataNascita().equals(data)){
                pazientiDataNascita.add(p);
            }
        }
        return pazientiDataNascita;
    }

    public List<Paziente> findByNomeCognome(String nome, String cognome) {
        List<Paziente> pazientiNomeCognome = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome)){
                pazientiNomeCognome.add(p);
            }
        }
        return pazientiNomeCognome;
    }

    public List<Paziente> findByNomeCognomeLuogo(String nome, String cognome, String cittàNascita) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNomeCognome = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getLuogoNascita().equals(cittàNascita)){
                pazientiNomeCognome.add(p);
            }
        }
        return pazientiNomeCognome;
    }

    public List<Paziente> findByNomeCognomeData(String nome, String cognome, String dataNascita) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNomeCognomeData = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getDataNascita().equals(dataNascita)){
                pazientiNomeCognomeData.add(p);
            }
        }
        return pazientiNomeCognomeData;
    }

    public List<Paziente> findByNomeCognomeDataLuogo(String nome, String cognome, String dataNascita, String luogo) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNomeCognomeData = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getDataNascita().equals(dataNascita) && p.getLuogoNascita().equals(luogo)){
                pazientiNomeCognomeData.add(p);
            }
        }
        return pazientiNomeCognomeData;
    }
}
