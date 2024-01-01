package cryobank.service;

import java.time.LocalDateTime;
import java.util.List;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;

public interface CryobankService {
	int getAvgValue(int sensorID, LocalDateTime from, LocalDateTime to);
	int getMaxValue(int sensorID, LocalDateTime from, LocalDateTime to);
	int getMinValue(int sensorID, LocalDateTime from, LocalDateTime to);
	SensorNitrogenDto getMaxValue(LocalDateTime from, LocalDateTime to);
	SensorNitrogenDto getMinValue(LocalDateTime from, LocalDateTime to);
	List<Integer> getAllValues(int sensorID, LocalDateTime from, LocalDateTime to);
	List<SensorNitrogenDto> getAllValues(LocalDateTime from, LocalDateTime to);
	
}
