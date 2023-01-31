package com.example.apigateway.stub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class Paziente {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String dataNascita;
    private Indirizzo indirizzo;
    private String cittàNascita;
}
