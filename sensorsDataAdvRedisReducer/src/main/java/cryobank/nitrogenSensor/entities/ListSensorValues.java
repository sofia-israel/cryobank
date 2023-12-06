package cryobank.nitrogenSensor.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.redis.core.RedisHash;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import lombok.Getter;

@RedisHash
@Getter
public class ListSensorValues {
	long sensorId;
	List<SensorNitrogenDto> values = new ArrayList<>();
	
	public ListSensorValues(long sensorId) {
		super();
		this.sensorId = sensorId;
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
		ListSensorValues other = (ListSensorValues) obj;
		return sensorId == other.sensorId;
	}
	
	
}
