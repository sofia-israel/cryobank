package cryobank.nitrogenSensor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cryobank.SensorsLastTimestampsControllerApplication;
import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorLastTimestamp;
import cryobank.nitrogenSensor.repo.SensorsTimestampsRepo;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TimestampsChecker {

	@Value("${app.sensor.timestampscontroller.binding.name}")
	String bindingName;
	
	@Autowired
	StreamBridge streamBridge;
	
	@Value("${app.check.timeout:1000}")
	long checkTimeout;
	
	ObjectMapper mapper = new ObjectMapper();
	SensorsTimestampsRepo sensorsTimestampsRepo;

	public void setSensorsTimestampsRepo(SensorsTimestampsRepo sensorsTimestampsRepo) {
		this.sensorsTimestampsRepo = sensorsTimestampsRepo;
	}
	
	@PostConstruct
	void startPeriodicalCheckOldTimespamps()	{
		Thread checkerThread = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(checkTimeout);
				} catch (InterruptedException e) {

				}
				checkAllTimestamps();
			}
		});
		checkerThread.setDaemon(true);
		checkerThread.start();
	}
	
	@PostConstruct
	void startPeriodicalCheckAllSensorsPresent()	{
		Thread checkerThread = new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(checkTimeout);
				} catch (InterruptedException e) {

				}
				checkAllTimestamps();
			}
		});
		checkerThread.setDaemon(true);
		checkerThread.start();
	}

	public void checkAllTimestamps() {
		// making check for all SensorsTimestamps records that we have in Redis
		long curTimestamp = System.currentTimeMillis();
		List<SensorLastTimestamp> timestampsList = (List<SensorLastTimestamp>) sensorsTimestampsRepo.findAll();
		if (timestampsList != null) {
			timestampsList.forEach(r -> {
				if (curTimestamp - r.getLastTimestamp() > 500) {
					log.debug("Timestamp for sensor {} is too old!!!", r.getSensorId());
					try {
						String message = mapper.writeValueAsString(new SensorNitrogenDto(r.getSensorId(), r.getLastTimestamp(), 0));
						log.debug("Timestamp is too old: {}", message);
						streamBridge.send(bindingName, message);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
	}
}
