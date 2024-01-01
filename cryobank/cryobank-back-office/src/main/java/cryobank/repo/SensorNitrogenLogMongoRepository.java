package cryobank.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import cryobank.entities.SensorNitrogenMongoDoc;

public interface SensorNitrogenLogMongoRepository extends MongoRepository<SensorNitrogenMongoDoc, Integer> {

}

