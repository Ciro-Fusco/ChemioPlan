package com.example.frontend.model;

import jakarta.validation.constraints.NotEmpty;
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
