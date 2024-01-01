package cryobank.entities;

import cryobank.api.ApiConstants;
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
@Table(name = ApiConstants.sensorNitrogenLogMongoDoc)
public class SensorNitrogenDBEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // to generate primary key automatically (alternative primary key is sensorID + timestamp)
	private int sensorID;
	private long timestamp;
	private int nitrogen_level_value;
	
	public SensorNitrogenDBEntity(int sensorID, long timestamp, int nitrogen_level_value) {
		super();
		this.sensorID = sensorID;
		this.timestamp = timestamp;
		this.nitrogen_level_value = nitrogen_level_value;
	}
	
}
