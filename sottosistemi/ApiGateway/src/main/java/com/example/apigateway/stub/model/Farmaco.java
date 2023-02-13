package com.example.apigateway.stub.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * <p>Questa classe rappresenta l'entità Farmaco.</p>
 *
 * @version 0.1
 */
@Data
@ToString
@AllArgsConstructor
public class Farmaco {

  private String codiceFarmaco;
  private String nome;
  private double dosaggio;
  private int numLotto;
  private String scandenza;
}