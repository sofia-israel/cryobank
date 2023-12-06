package cryobank.nitrogenSensor.repo;

import org.springframework.data.repository.CrudRepository;

import cryobank.nitrogenSensor.entities.ListSensorValues;

public interface SensorsDataRepo extends CrudRepository<ListSensorValues, Integer> {

}
