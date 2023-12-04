package cryobank.nitrogenSensor.service;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorNitrogenDBEntity;
import cryobank.nitrogenSensor.repo.SensorsRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DBWriter {
	
	// пишем пришедшие данные в SQL базу
    ObjectMapper mapper = new ObjectMapper();
     
    @Autowired
    SensorsRepository repository;

     @Bean
     public Consumer<String> receiveSensorData() {
    	 // мы возвращаем функцию, которая что-то получает, но ничего не возвращает
 
    	 return data -> {
    		 SensorNitrogenDto sensorNitrogenDto = null;
    		 try {
    			 log.trace("receiveSensorData - OK");
    			 sensorNitrogenDto = mapper.readValue(data, SensorNitrogenDto.class);
    			 if (sensorNitrogenDto.nitrogen_level_value > 250)
    			 { // temporary check!!!!!
    				 log.debug("Alarm!!!!!!");
    			 }
    			 else
    			 {
    				 log.debug("Normal data received");
    			 }
    		 } catch (Exception e) {
    			 log.debug("receiveSensorData - wrong Data!");
    		 }
    		 
    		 SensorNitrogenDBEntity record = new SensorNitrogenDBEntity(sensorNitrogenDto.sensorID, sensorNitrogenDto.timestamp, sensorNitrogenDto.nitrogen_level_value);
    		 repository.save(record);
    	 };
     }
}
