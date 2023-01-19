package com.chemioplan.SchedaPaziente.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * <p>Questa classe rappresenta l'entità Paziente</p>
 *
 * @author vitco
 * @version 0.1
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "pazienti")

public class SchedaPaziente {
    /**
     * <p> Id della schedaPaziente</p>
     */
    @Id
    @Field(name ="id")
    private int id;

    /**
     * <p>CodiceFiscale dell paziente</p>
     */
    @Field(name ="codiceFiscale")
    private String codiceFiscale;

    /**
     *<p>Codice dei farmaci che il paziente assume</p>
     */
    @Field(name="codiceFarmaci")
    private List<String> codiceFarmaci;

    /**
     * <p>nome delle malattie da cui è affetto il paziente</p>
     */
    @Field(name="malattie")
    private List<String> malattie;
}
