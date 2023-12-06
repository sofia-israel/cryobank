package cryobank.nitrogenSensor.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.ListSensorValues;
import cryobank.nitrogenSensor.repo.SensorsDataRepo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AvgReducerImpl implements AvgReducer {

	@Autowired
	SensorsDataRepo sensorsDataRepo;

	@Value("${app.reducing.size:100}")
	int reducingSize;

	@Override
	public SensorNitrogenDto avgReduce(SensorNitrogenDto currentData) {
		// Return null of average value if reducingSize is reached
		SensorNitrogenDto res = null;
		int sensorId = currentData.sensorID;
		ListSensorValues listSensorValues = sensorsDataRepo.findById(sensorId).orElse(null);
		if (listSensorValues == null)
		{ // the first time
			log.debug("sensor {} not found in Redis", sensorId);
			listSensorValues = new ListSensorValues(sensorId);
		}
		
		List<SensorNitrogenDto> values = listSensorValues.getValues();
		values.add(currentData);
		if (values.size() == reducingSize)
		{
			res = new SensorNitrogenDto(sensorId);
			res.nitrogen_level_value = values.stream().collect(Collectors.averagingInt(v -> v.nitrogen_level_value)).intValue();
			res.timestamp = System.currentTimeMillis(); // set new timestamp
			log.debug("computed avg value {} for sensor {}", res, sensorId);
			values.clear();//clean list for average calculating - start new iteration
		}
		sensorsDataRepo.save(listSensorValues);
		log.trace("Redis updated for sensor{}", sensorId);

		return res;
	}

}
