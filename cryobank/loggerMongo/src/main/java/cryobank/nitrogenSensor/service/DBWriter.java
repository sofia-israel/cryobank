package cryobank.nitrogenSensor.service;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorNitrogenMongoDoc;
import cryobank.nitrogenSensor.repo.SensorsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DBWriter {
	
	// We are writing input data to SQL database
    ObjectMapper mapper = new ObjectMapper();
     
    @Autowired
    SensorsRepository repository;

     @Bean
     public Consumer<String> receiveSensorData() {
    	 return data -> {
    		 SensorNitrogenDto sensorNitrogenDto = null;
    		 try {
    			 log.trace("MongoLogger: receiveSensorData - OK");
    			 sensorNitrogenDto = mapper.readValue(data, SensorNitrogenDto.class);
    		 } catch (Exception e) {
    			 log.debug("MongoLogger: receiveSensorData - wrong Data!");
    		 }
    		 
    		 SensorNitrogenMongoDoc record = new SensorNitrogenMongoDoc(sensorNitrogenDto.sensorID, sensorNitrogenDto.timestamp, sensorNitrogenDto.nitrogen_level_value);
    		 repository.save(record);
    	 };
     }
}
