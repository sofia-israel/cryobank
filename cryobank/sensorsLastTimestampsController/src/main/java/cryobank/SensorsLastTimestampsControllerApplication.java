package cryobank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ConfigurableApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorLastTimestamp;
import cryobank.nitrogenSensor.repo.SensorsTimestampsRepo;
import cryobank.nitrogenSensor.service.CurrentTimestampToRedisWriterImpl;
import cryobank.nitrogenSensor.service.TimestampsChecker;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class SensorsLastTimestampsControllerApplication {

	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext cac = SpringApplication.run(SensorsLastTimestampsControllerApplication.class, args);
		SensorsTimestampsRepo sensorsTimestampsRepo = cac.getBean(SensorsTimestampsRepo.class);

		TimestampsChecker timestampsChecker = cac.getBean(TimestampsChecker.class);
		timestampsChecker.setSensorsTimestampsRepo(sensorsTimestampsRepo);
		
		while (true) {
			Thread.sleep(1000);
			timestampsChecker.checkAllTimestamps();
		}
	}
}


/*
 * Yuri!!!!!!!

package cryobank;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.CloseableThreadContext.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class tets {
@Value("${app.check.timeout:1000}")
long checkTimeout;

Instant timestamp;

@Value("${app.time.period.poll.seconds:3600}")
private long timePeriodSensorsDataPoll;

// List<Sensor> sensors=new ArrayList
//final SensorDataProviderClient client; //interface with only method getSensorData 
//returning a list of sensors

@PostConstruct

void startPeriod() {
	Thread checkerThread=new Thread(()->
	{
		while (true) {
		try {
			Thread.sleep(checkTimeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	//	checkSensors();
	}
			
			
			);
	checkerThread.setDaemon(true);

}
private void checkSensors() {

	if (timestamp==null || ChronoUnit.SECONDS.between(timestamp,Instant.now())>timePeriodSensorsDataPoll)
	{
		//communicating with synchronous service for sensors data
		// sensors=client.getSensorsData();
		timestamp=Instant.now();
		//Запись о неготовности не удаляется
	}
	
}
	
	
}


 */
