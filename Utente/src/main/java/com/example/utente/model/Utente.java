package com.example.utente.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Utente")
public class Utente {
    @Id @Field("_id")
    private Integer id;
    private String nome;
    private String cognome;
    private Credenziali credenziali;
    private String ruolo;
}
