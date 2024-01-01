package cryobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DBWriterApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DBWriterApplication.class, args);
	}

}
