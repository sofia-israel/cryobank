package cryobank.nitrogenSensor.repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import cryobank.nitrogenSensor.entities.SensorNitrogenMongoDoc;

public interface SensorsRepository extends MongoRepository<SensorNitrogenMongoDoc, Integer> {
	
}
