package cryobank.nitrogenSensor.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SensorNitrogenGetDataImpl implements ISensorNitrogenGetData {

	@Value("${app.min_value:30}")
	private int minValue;
	@Value("${app.max_value:250}")
	private int maxValue;
	
	@Override
	public SensorNitrogenDto getSensorNitrogenData(int sensorID) {
		log.trace("SensorNitrogen sent data for sensorId {}", sensorID);
		return getRandomSensorData(sensorID);
	}
	
	@Override
	public SensorNitrogenDto getSensorAlarmNitrogenData(int sensorID) {
		log.trace("SensorNitrogen sent ALARM data for sensorId {}", sensorID);
	    SensorNitrogenDto data = getRandomSensorData(sensorID);
	    data.nitrogen_level_value = maxValue + 10;
		return data;
	}
	
	private SensorNitrogenDto getRandomSensorData(int sensorID) {
		long timestamp = System.currentTimeMillis();
		int value = getRandomNumber(minValue, maxValue);
		return new SensorNitrogenDto(sensorID, timestamp, value);
	}

	private int getRandomNumber(int min, int max) {
		return new Random().ints(1, min, max+1).findFirst().getAsInt();
	}

}
