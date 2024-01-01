package cryobank.nitrogenSensor.service;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;

public interface ICurrentTimestampToRedisWriter {
      void writeCurrentTimestampToRedis(SensorNitrogenDto currentData);
}
