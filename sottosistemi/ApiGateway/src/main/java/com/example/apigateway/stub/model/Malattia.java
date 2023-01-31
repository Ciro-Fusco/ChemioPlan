package com.example.apigateway.stub.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Malattia {
    private String codiceMalattia;
    private String nomeMalattia;
    private String parteCorpo;
}