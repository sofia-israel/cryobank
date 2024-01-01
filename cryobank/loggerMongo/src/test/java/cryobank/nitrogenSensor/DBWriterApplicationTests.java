package cryobank.nitrogenSensor;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.SensorNitrogenMongoDoc;
import cryobank.nitrogenSensor.repo.SensorsRepository;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
class DBWriterApplicationTests {
	private static final int SENSOR_ID1 = 123;
	private static final int LEVEL1 = 70;
	private static final int SENSOR_ID2 = 124;
	private static final int LEVEL2 = 75;
	
	@Autowired
	InputDestination producer;

	@Autowired
	SensorsRepository sensorsRepository;
	SensorNitrogenDto sensorData1 = new SensorNitrogenDto(SENSOR_ID1, 0, LEVEL1);
	SensorNitrogenDto sensorData2 = new SensorNitrogenDto(SENSOR_ID2, 0, LEVEL2);
	SensorNitrogenMongoDoc doc1 = new SensorNitrogenMongoDoc(sensorData1.sensorID, sensorData1.timestamp, sensorData1.nitrogen_level_value);
	SensorNitrogenMongoDoc doc2 = new SensorNitrogenMongoDoc(sensorData2.sensorID, sensorData2.timestamp, sensorData2.nitrogen_level_value);
	String bindingName = "receiveSensorData-in-0";
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void test() {
		producer.send(new GenericMessage<SensorNitrogenDto>(sensorData1), bindingName);
		producer.send(new GenericMessage<SensorNitrogenDto>(sensorData2), bindingName);
		List<SensorNitrogenMongoDoc> documents = sensorsRepository.findAll();
		List<SensorNitrogenMongoDoc> expected = Arrays.asList(doc1, doc2);
		assertIterableEquals(expected, documents);
	}

}
