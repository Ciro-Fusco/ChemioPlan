package com.example.FrontEnd.FrontEnd.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paziente {
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String dataNascita;
    private Indirizzo indirizzo;
    private String luogoNascita;
}
