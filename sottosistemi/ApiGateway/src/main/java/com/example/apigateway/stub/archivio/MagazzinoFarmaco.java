package com.example.apigateway.stub.archivio;


import com.example.apigateway.stub.model.Farmaco;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
public class MagazzinoFarmaco {
    List<Farmaco> farmaci = new ArrayList<>();

    public void initialize()  {
        farmaci.add(new Farmaco("codice1", "nome1", 1000.00, 1,"29-01-2023"));
        farmaci.add(new Farmaco("codice2", "nome2", 500.00, 1,"29-01-2023"));
        farmaci.add(new Farmaco("codice3", "nome3", 250.00, 1,"29-01-2023"));
        farmaci.add(new Farmaco("codice4", "nome4", 750.00, 1,"29-01-2023"));
    }

    public Farmaco findById(String id){
        List<String> codici = farmaci.stream().map((f)->f.getCodiceFarmaco()).toList();
        return farmaci.get(codici.indexOf(id));
    }


}
