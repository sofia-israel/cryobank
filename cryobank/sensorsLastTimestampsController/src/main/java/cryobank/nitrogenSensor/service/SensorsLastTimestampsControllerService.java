package cryobank.nitrogenSensor.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class SensorsLastTimestampsControllerService {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	AvgReducerRedisDataTest avgReducerRedisDataTest = new AvgReducerRedisDataTest();
	
	@Autowired
	ICurrentTimestampToRedisWriter currentTimestampToRedisWriter;

	// we take data from sensors, calculate average and put it to output channel
	@Bean
	public Consumer<String> receiveSensorData() {
		return data -> {
			SensorNitrogenDto sensorNitrogenDto = null;
			try {
				log.trace("SensorsLastTimestampsControllerService: receiveSensorData - OK");
				sensorNitrogenDto = mapper.readValue(data, SensorNitrogenDto.class);
				//avgReducerRedisDataTest.avgReduce(sensorNitrogenDto);
				
				currentTimestampToRedisWriter.writeCurrentTimestampToRedis(sensorNitrogenDto);

			} catch (Exception e) {
				log.debug("SensorsLastTimestampsControllerService: receiveSensorData - wrong Data!");
			}
		};
	}
}
