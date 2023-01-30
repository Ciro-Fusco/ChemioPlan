package com.example.apigateway.stub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
@AllArgsConstructor
public class Farmaco {
    private String codiceFarmaco;
    private String nome;
    private double dosaggio;
    private int numLotto;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date scandenza;
}