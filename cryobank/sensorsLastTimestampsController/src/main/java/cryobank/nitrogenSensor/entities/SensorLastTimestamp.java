package cryobank.nitrogenSensor.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import lombok.Getter;

@RedisHash
@Getter
public class SensorLastTimestamp {
	@Id
	int sensorId;
	long lastTimestamp;
	
	public SensorLastTimestamp(int sensorId, long lastTimestamp) {
		super();
		this.sensorId = sensorId;
		this.lastTimestamp = lastTimestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sensorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SensorLastTimestamp other = (SensorLastTimestamp) obj;
		return sensorId == other.sensorId;
	}
	
	
}
