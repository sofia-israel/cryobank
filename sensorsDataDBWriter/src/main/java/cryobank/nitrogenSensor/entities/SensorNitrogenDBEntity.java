package cryobank.nitrogenSensor.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = "id")

@Entity
@Table(name = "sensorsData")
public class SensorNitrogenDBEntity {
	@Id
	public int id; // просто id записи. Надо подумать над ключом!!!
	private int sensorID;
	private long timestamp;
	private int nitrogen_level_value;
	
	private static int curNum = 1; // но это неправильно. Вдруг у нас будут две таких микросервиса
	
	public SensorNitrogenDBEntity(int sensorID, long timestamp, int nitrogen_level_value) {
		super();
		this.id = curNum++;
		this.sensorID = sensorID;
		this.timestamp = timestamp;
		this.nitrogen_level_value = nitrogen_level_value;
	}
	
	
}
