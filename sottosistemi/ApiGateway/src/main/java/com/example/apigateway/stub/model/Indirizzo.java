package com.example.apigateway.stub.model;

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