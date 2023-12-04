package cryobank.nitrogenSensor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cryobank.nitrogenSensor.entities.SensorNitrogenDBEntity;

public interface SensorsRepository extends JpaRepository<SensorNitrogenDBEntity, Integer> {
	
}
