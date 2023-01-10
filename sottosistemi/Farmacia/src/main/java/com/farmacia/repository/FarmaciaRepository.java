package com.farmacia.repository;

import com.farmacia.model.SchedaFarmaco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FarmaciaRepository extends MongoRepository<SchedaFarmaco, String> {}
