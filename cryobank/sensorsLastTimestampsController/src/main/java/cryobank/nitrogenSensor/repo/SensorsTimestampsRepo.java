package cryobank.nitrogenSensor.repo;

import org.springframework.data.repository.CrudRepository;

import cryobank.nitrogenSensor.entities.SensorLastTimestamp;

public interface SensorsTimestampsRepo extends CrudRepository<SensorLastTimestamp, Integer> {

}