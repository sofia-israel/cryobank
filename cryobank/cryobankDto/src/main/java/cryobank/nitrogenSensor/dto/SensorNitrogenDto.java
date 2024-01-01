package cryobank.nitrogenSensor.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SensorNitrogenDto {
	public int sensorID;
	public long timestamp;
	public int nitrogen_level_value;
	
	public SensorNitrogenDto(int sensorID) {
		super();
		this.sensorID = sensorID;
	}

	public SensorNitrogenDto(int sensorID, long timestamp, int nitrogen_level_value) {
		this(sensorID);
		this.timestamp = timestamp;
		this.nitrogen_level_value = nitrogen_level_value;
	}
	
	
}
