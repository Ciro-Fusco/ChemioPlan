package com.example.apigateway.stub.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * <p>Questa classe rappresenta l'entità Malattia.</p>
 *
 * @version 0.1
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Malattia {

  private String codiceMalattia;
  private String nomeMalattia;
  private String parteCorpo;
}