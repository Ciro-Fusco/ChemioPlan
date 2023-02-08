package com.example.FrontEnd.FrontEnd.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
  @NotEmpty(message = "Username obbligatorio")
  private String user;
  /**
  * <p>Password.</p>
  */
  @NotEmpty(message = "Password obbligatoria")
  private String pass;
}
