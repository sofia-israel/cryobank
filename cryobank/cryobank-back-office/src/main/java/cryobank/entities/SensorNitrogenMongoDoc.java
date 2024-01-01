package cryobank.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import cryobank.api.*;

@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode(of = "id")

@Document(collection = ApiConstants.sensorNitrogenLogMongoDoc)
public class SensorNitrogenMongoDoc {
	//@Id
	//public int id; // lets create id automatically (?)
	private int sensorID;
	private long timestamp;
	private int nitrogen_level_value;
	
	public SensorNitrogenMongoDoc(int sensorID, long timestamp, int nitrogen_level_value) {
		super();
		this.sensorID = sensorID;
		this.timestamp = timestamp;
		this.nitrogen_level_value = nitrogen_level_value;
	}
	
	
}
