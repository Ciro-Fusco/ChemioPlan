package com.example.FrontEnd.FrontEnd.model;

import lombok.Data;

/**
 * <p>Questa classe modella le credenziali dell'entità utente.</p>
 *
 * @author chris
 * @version 1.0
 */
@Data
public class Credenziali {
  /**
  * <p>Username.</p>
  */
  private String user;
  /**
  * <p>Password.</p>
  */
  private String pass;
}
