package cryobank.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import cryobank.entities.SensorNitrogenMongoDoc;
import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.repo.SensorNitrogenLogMongoRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CryobankServiceMongoImpl implements CryobankService{

	@Autowired
	MongoTemplate template;
	
	@Autowired
	SensorNitrogenLogMongoRepository repo;
	
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
		List<SensorNitrogenMongoDoc> res = repo.findAll();
		return res.stream().map(doc -> new SensorNitrogenDto(doc.getSensorID(), doc.getTimestamp(), doc.getNitrogen_level_value())).toList();
	}

}
