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
public class AdvReducerService {

	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	AvgReducer avgReducer;

	@Autowired
	StreamBridge streamBridge;
	
	@Value("${app.sensor.avgproducer.binding.name}")
	String bindingName;

	// we take data from sensors, calculate average and put it to output channel
	@Bean
	public Consumer<String> receiveSensorData() {
		return data -> {
			SensorNitrogenDto sensorNitrogenDto = null;
			try {
				log.trace("AdvReducerService: receiveSensorData - OK");
				sensorNitrogenDto = mapper.readValue(data, SensorNitrogenDto.class);
				if (sensorNitrogenDto.nitrogen_level_value > 250) { // temporary check!!!!!
					log.debug("AdvReducerService: Alarm!!!!!!");
				} else {
					log.trace("AdvReducerService: Normal data received");
				}

				SensorNitrogenDto avg = avgReducer.avgReduce(sensorNitrogenDto);
				if (avg != null)
					streamBridge.send(bindingName, data);

			} catch (Exception e) {
				log.debug("AdvReducerService: receiveSensorData - wrong Data!");
			}
		};
	}
}
