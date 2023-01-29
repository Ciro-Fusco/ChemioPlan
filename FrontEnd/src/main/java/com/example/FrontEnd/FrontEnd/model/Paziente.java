package com.example.FrontEnd.FrontEnd.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>Questa classe rappresenta l'entità Paziente</p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paziente {

    private String codiceFiscale;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date data;
    private String nome;
    private String cognome;
    private String luogo_nascita;

}
