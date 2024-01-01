package cryobank.nitrogenSensor;

import cryobank.nitrogenSensor.repo.SensorsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

@Import(TestChannelBinderConfiguration.class)
@SpringBootTest
class DBWriterApplicationTests {
	@Autowired
	InputDestination producer;
	@Autowired
	OutputDestination consumer;

	@MockBean
	SensorsRepository sensorsRepository;
	
	@Test
	void contextLoads() {
	}

}
