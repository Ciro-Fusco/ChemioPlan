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
@Document(collection = "Pazienti")

public class SchedaPaziente {
    /**
     * <p> Id della schedaPaziente</p>
     */
    @Id
    @Field(name ="_id")
    private int Id;

    /**
     * <p>CodiceFiscale dell paziente</p>
     */
    @Field(name ="CodiceFiscale")
    private String CodiceFiscale;

    /**
     *<p>Codice dei farmaci che il paziente assume</p>
     */
    @Field(name="CodiceFarmaci")
    private List<String> codiceFarmaci;

    /**
     * <p>nome delle malattie da cui è affetto il paziente</p>
     */
    @Field(name="MalattieList")
    private List<String> malattie;
}
