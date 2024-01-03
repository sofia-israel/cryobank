package cryobank.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cryobank.nitrogenSensor.dto.SensorNitrogenDto;
import cryobank.service.CryobankService;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/cryobank")
@Validated
public class CryobankController {
	private static final String ISO_DATE_TIME = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}";

	@Autowired
	CryobankService service;

	@GetMapping("avg/{id}")
	Integer getAvgValue(@PathVariable("id") int sensorID,
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getAvgValue(sensorID, from, to);
	}

	@GetMapping("max/{id}")
	public int getMaxValue(@PathVariable("id") int sensorID,
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getMaxValue(sensorID, from, to);
	}

	@GetMapping("min/{id}")
	public int getMinValue(@PathVariable("id") int sensorID,
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getMinValue(sensorID, from, to);
	}

	@GetMapping("maxdata")
	public SensorNitrogenDto getMaxValue(
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getMaxValue(from, to);
	}

	@GetMapping("mindata")
	public SensorNitrogenDto getMinValue(
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getMinValue(from, to);
	}

	@GetMapping("all/{id}")
	public List<Integer> getAllValues(@PathVariable("id") int sensorID,
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getAllValues(sensorID, from, to);
	}

	@GetMapping("all")
	public List<SensorNitrogenDto> getAllValues(
			@RequestParam(name = "from", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'from'") String fromDateTime,
			@RequestParam(name = "to", required = false) @Pattern(regexp = ISO_DATE_TIME, message = "wrong date/time format 'to'") String toDateTime) 
	{
		LocalDateTime from = fromDateTime == null ? LocalDateTime.of(1000, 1, 1, 0, 0) : LocalDateTime.parse(fromDateTime);
		LocalDateTime to = toDateTime == null ? LocalDateTime.of(10000, 1, 1, 0, 0) : LocalDateTime.parse(toDateTime);
		return service.getAllValues(from, to);
	}

}
