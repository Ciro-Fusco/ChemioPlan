package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Indirizzo {
    private String via;
    private String citta;
    private String paese;
    private String cap;
}