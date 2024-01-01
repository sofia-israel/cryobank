package cryobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"cryobank"})
public class CryobankAppl {
	public static void main(String[] args) {
		SpringApplication.run(CryobankAppl.class, args);

	}
}
