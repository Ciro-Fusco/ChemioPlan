package com.example.apigateway.stub.archivio;

import com.example.apigateway.stub.model.Malattia;
import com.example.apigateway.stub.model.Paziente;
import lombok.Data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class ArchivioMalattia {
    List<Malattia> malattie = new ArrayList<>();

    public void initialize(){
        malattie.add(new Malattia("codice1", "nome1", "testa"));
        malattie.add(new Malattia("codice2", "nome2", "polmoni"));
        malattie.add(new Malattia("codice3", "nome3", "pancreas"));
        malattie.add(new Malattia("codice4", "nome4", "cervello"));
        malattie.add(new Malattia("codice5", "nome5", "fegato"));
    }

    public Malattia findByCodice(String codice) {
        List<String> codici = malattie.stream().map((m)->m.getCodiceMalattia()).toList();
        return malattie.get(codici.indexOf(codice));
    }

    public List<Malattia> findByNome(String nome) {
        Iterator<Malattia> iterator = malattie.listIterator();
        List malattieNome = new ArrayList<>();
        for (Malattia m : malattie){
            if(m.getNomeMalattia().equals(nome)){
                malattieNome.add(m);
            }
        }
        return malattieNome;
    }

    public List<Malattia> findByParteCorpo(String parteCorpo) {
        Iterator<Malattia> iterator = malattie.listIterator();
        List malattieParteCorpo = new ArrayList<>();
        for (Malattia m : malattie){
            if(m.getParteCorpo().equals(parteCorpo)){
                malattieParteCorpo.add(m);
            }
        }
        return malattieParteCorpo;
    }
}
