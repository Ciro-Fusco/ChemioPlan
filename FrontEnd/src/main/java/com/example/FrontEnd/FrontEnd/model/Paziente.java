package com.example.FrontEnd.FrontEnd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paziente {
    @Pattern(regexp = "[A-Z]{6}[0-9]{2}[A-Z]{1}[0-9]{2}[A-Z]{1}[0-9]{3}[A-Z]{1}|^.{0}", message = "Codice Fiscale Invalido")
    private String codiceFiscale;
    private String nome;
    private String cognome;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String dataNascita;
    private Indirizzo indirizzo;
    private String luogoNascita;
}
