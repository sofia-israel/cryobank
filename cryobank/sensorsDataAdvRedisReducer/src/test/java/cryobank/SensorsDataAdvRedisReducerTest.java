package cryobank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.nitrogenSensor.entities.ListSensorValues;
import cryobank.nitrogenSensor.repo.SensorsDataRepo;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
public class SensorsDataAdvRedisReducerTest {

	
	@Autowired
	InputDestination producer;
	@Autowired
	OutputDestination consumer;
	@MockBean
	SensorsDataRepo sensorsDataRepo;
	
	static long currentTime = System.currentTimeMillis();
	private static final int S1_ID = 1;
	private static final int S2_ID = 2;
	private static final long V1_TIMESTAMP = currentTime - 100;
	private static final long V2_TIMESTAMP = currentTime - 50;
	private static final long V3_TIMESTAMP = currentTime;
	private static final int V1_DATA = 150;
	private static final int V2_DATA = 100;
	private static final int V3_DATA = 50;
	private static final SensorNitrogenDto sensorData1_1 = new SensorNitrogenDto(S1_ID, V1_TIMESTAMP, V1_DATA);
	private static final SensorNitrogenDto sensorData1_2 = new SensorNitrogenDto(S1_ID, V2_TIMESTAMP, V2_DATA);
	private static final SensorNitrogenDto sensorData1_3 = new SensorNitrogenDto(S1_ID, V3_TIMESTAMP, V3_DATA);
	
	private static final SensorNitrogenDto sensorData2 = new SensorNitrogenDto(S2_ID, V3_TIMESTAMP, V3_DATA); // additional second sensor
	
	static HashMap<Integer, ListSensorValues> redisMap = new HashMap<>();
	
	private String producerBindingName = "avg_data_producer";
	private String consumerBindingName = "sensor_nitrogen-data";
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void firstValuesTest() {
		when(sensorsDataRepo.findById(S1_ID))
		.thenReturn(Optional.ofNullable(null));
		when(sensorsDataRepo.save(new ListSensorValues(S1_ID)))
	        .thenAnswer(new Answer<ListSensorValues>() {

			@Override
			public ListSensorValues answer(InvocationOnMock invocation) throws Throwable {
				redisMap.put(S1_ID, invocation.getArgument(0));
				return invocation.getArgument(0);
			}
		});
		producer.send(new GenericMessage<SensorNitrogenDto>(sensorData1_1), consumerBindingName);
		

		Message<byte[]> message = consumer.receive(100, producerBindingName );
		assertNull(message); // there is no average value yet
		assertEquals(sensorData1_1.nitrogen_level_value, redisMap.get(S1_ID).getValues().get(0).nitrogen_level_value); // no average but there is a new value in the map
	}
	
	@Test
	void middleValuesTest() {
		//assertEquals(1, 0);
	}
	
	@Test
	void lastValuesForAverigingTest() {
	}

}
