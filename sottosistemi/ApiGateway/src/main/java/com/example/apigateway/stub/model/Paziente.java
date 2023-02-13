package com.example.apigateway.stub.model;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * <p>Questa classe rappresenta l'entità Paziente.</p>
 *
 * @version 0.1
 */
@Data
@AllArgsConstructor
public class Paziente {

  private String codiceFiscale;
  private String nome;
  private String cognome;
  private String dataNascita;
  private Indirizzo indirizzo;
  private String luogoNascita;
}
