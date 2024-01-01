package cryobank.nitrogenSensor.service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;

public interface AvgReducer {
	// Returns average value or null if it still can not be calculated
	SensorNitrogenDto avgReduce(SensorNitrogenDto currentData);
}
