package cryobank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class CryobankServiceSqlImpl implements CryobankService{

	@Override
	public int getAvgValue(int sensorID, LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxValue(int sensorID, LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMinValue(int sensorID, LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SensorNitrogenDto getMaxValue(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SensorNitrogenDto getMinValue(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getAllValues(int sensorID, LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SensorNitrogenDto> getAllValues(LocalDateTime from, LocalDateTime to) {
		// TODO Auto-generated method stub
		return null;
	}

}
