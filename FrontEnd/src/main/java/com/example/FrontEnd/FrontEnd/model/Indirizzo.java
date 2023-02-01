package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indirizzo {
    private String via;
    private String citta;
    private String paese;
    private String cap;
}