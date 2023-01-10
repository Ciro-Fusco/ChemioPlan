package com.example.prenotazione.config;

import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Questa classa viene utilizzata per configurare il Database.
 *
 * @author Alessandro Clericuzio
 * @version n.1 (10-01-2023)
 */
@Configuration
public class MongoConfig {

  /**
   * <p>Questo metodo viene usato per creare un Database 'ChemioPlan'.</p>
   *
   * @return resituisce un nuovo DB.
   */
  @Bean
  public MongoDatabaseFactory factory() {
    return new SimpleMongoClientDatabaseFactory(MongoClients.create(), "ChemioPlan");
  }

  /**
   * <p>Questo metodo viene usato generare un template.</p>
   *
   * @return restituisce un nuovo template
   */
  public MongoTemplate template() {
    MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory()),
        new MongoMappingContext());
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return new MongoTemplate(factory(), converter);
  }
}
