package cryobank.nitrogenSensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorLastTimestamp;
import cryobank.nitrogenSensor.repo.SensorsDataRepo;
import cryobank.nitrogenSensor.repo.SensorsTimestampsRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CurrentTimestampToRedisWriterImpl implements ICurrentTimestampToRedisWriter {

	@Autowired
	SensorsTimestampsRepo sensorsTimestampsRepo;
	
	@Override
	public void writeCurrentTimestampToRedis(SensorNitrogenDto currentData) {
		
		SensorLastTimestamp t = new SensorLastTimestamp(currentData.sensorID, currentData.timestamp);
		sensorsTimestampsRepo.save(t);
		log.trace("Redis sensorsTimestampsRepo updated for sensor{}", currentData.sensorID);

	}

}
