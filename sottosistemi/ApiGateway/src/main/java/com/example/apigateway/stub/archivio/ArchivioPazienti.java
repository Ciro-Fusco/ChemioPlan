package com.example.apigateway.stub.archivio;

import com.example.apigateway.stub.model.Indirizzo;
import com.example.apigateway.stub.model.Paziente;
import lombok.Data;

import java.text.*;
import java.util.*;


@Data
public class ArchivioPazienti {
    List<Paziente> pazienti = new ArrayList<>();
    SimpleDateFormat formato = new SimpleDateFormat("dd-mm-yyyy");
    public void initialize() throws ParseException {
        pazienti.add(new Paziente("xxxxx", "nome1", "cognome1", formato.parse("14-10-2024") ,new Indirizzo("via1", "città1", "paese1", "cap1"),"paese1"));
        pazienti.add(new Paziente("xxxxx1", "nome2", "cognome2", formato.parse("10-10-2024"),new Indirizzo("via2", "citta2", "paese2", "cap"),"paese2"));
        pazienti.add(new Paziente("xxxxx2", "nome3", "cognome3", formato.parse("10-10-2024"),new Indirizzo("via3", "città3", "paese2", "cap2"),"paese2"));
        pazienti.add(new Paziente("xxxxx3", "nome1", "cognome1", formato.parse("10-10-2024") ,new Indirizzo("via4", "città2", "paese4", "cap3"),"paese1"));
        pazienti.add(new Paziente("xxxxx4", "nome5", "cognome5", formato.parse("10-10-2024"),new Indirizzo("via5", "città4", "paese2", "cap4"),"paese4"));
        pazienti.add(new Paziente("xxxxx5", "nome6", "cognome6", formato.parse("10-10-2024"), new Indirizzo("via6", "città7", "paese7", "cap5"),"paese3"));
        pazienti.add(new Paziente("xxxxx6", "nome7", "cognome7", formato.parse("10-10-2024"),  new Indirizzo("via7", "città2", "paese3", "cap6"),"paese1"));
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
            if(p.getCittàNascita().equals(luogo)){
                pazientiLuogoNascita.add(p);
            }
        }
        return pazientiLuogoNascita;
    }

    public List<Paziente> findByDataNascita(String data) throws ParseException {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiDataNascita = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getDataNascita().equals(formato.parse(data))){
                pazientiDataNascita.add(p);
            }
        }
        return pazientiDataNascita;
    }

    public List<Paziente> findByNomeCognome(String nome, String cognome) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNomeCognome = new ArrayList<>();
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
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getCittàNascita().equals(cittàNascita)){
                pazientiNomeCognome.add(p);
            }
        }
        return pazientiNomeCognome;
    }

    public List<Paziente> findByNomeCognomeData(String nome, String cognome, Date dataNascita) {
        Iterator<Paziente> iterator = pazienti.listIterator();
        List pazientiNomeCognome = new ArrayList<>();
        for (Paziente p : pazienti){
            if(p.getNome().equals(nome) && p.getCognome().equals(cognome) && p.getDataNascita().equals(dataNascita)){
                pazientiNomeCognome.add(p);
            }
        }
        return pazientiNomeCognome;
    }
}
