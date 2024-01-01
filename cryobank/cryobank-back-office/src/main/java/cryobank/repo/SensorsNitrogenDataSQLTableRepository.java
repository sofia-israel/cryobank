package cryobank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import cryobank.entities.SensorNitrogenDBEntity;

public interface SensorsNitrogenDataSQLTableRepository  extends JpaRepository<SensorNitrogenDBEntity, Integer> {

}


