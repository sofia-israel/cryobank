package cryobank.nitrogenSensor.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.ListSensorValues;
import cryobank.nitrogenSensor.entities.SensorLastTimestamp;
import cryobank.nitrogenSensor.repo.SensorsDataRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AvgReducerRedisDataTest {

	@Autowired
	SensorsDataRepo sensorsDataRepo;

	@Value("${app.reducing.size:100}")
	int reducingSize;

	public void avgReduce(SensorNitrogenDto currentData) {
		// Just reading for test that we cat read data which another microservice wrote!!!
		int sensorId = currentData.sensorID;
		ListSensorValues listSensorValues = sensorsDataRepo.findById(sensorId).orElse(null);
		if (listSensorValues == null || listSensorValues.getValues() == null)
		{
			log.trace("No values in Readis!");
			return;
		}
		List<SensorNitrogenDto> values = listSensorValues.getValues();
		if (values.size() == reducingSize - 1)
		{
			log.debug("!!!!!!!OK");
			//values.clear();//clean list for average calculating - start new iteration
		}
		else
		{
			log.trace("values.size() in redis = {}", values.size());
		}
	}

}
