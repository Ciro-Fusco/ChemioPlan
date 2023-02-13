package com.example.apigateway.stub.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>Questa classe rappresenta l'entità Indirizzo.</p>
 *
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class Indirizzo {

  private String via;
  private String citta;
  private String paese;
  private String cap;
}