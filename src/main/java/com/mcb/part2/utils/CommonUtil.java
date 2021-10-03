package com.mcb.part2.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtil {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ApplicationContext applicationContext;
	
	
	public <T> T modalMap(Object ob, Class<T> type) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(ob, type);

	}

	public <S, T> List<T> modalMap(Collection<S> ob, Class<T> type) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return ob.stream().map(element -> modelMapper.map(element, type)).collect(Collectors.toList());

	}

	public void modalMapCopy(Object source, Object destination) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		modelMapper.map(source, destination);

	}

	public <T> T ojectMap(Object ob, Class<T> type) {
		return objectMapper.convertValue(ob, type);

	}

	
	
	public String objecToJson(Object object) {

		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

	}



	public String getNewVesrion() {
		int number = 1;
		return String.format("%02d", number);

	}

	public String getNewVesrion(String currentVersion) {
		int number = 1;
		if (currentVersion != null) {
			number = Integer.parseInt(currentVersion);
		}
		return String.format("%02d", ++number);

	}

	public Date getCurrentDate() {
		return new Date();
	}

	public Long genrateUniqueId() {
		return Calendar.getInstance().getTimeInMillis();
	}

	public static Double getPercantageValue(Double value, Double percantage) {
		return (value * percantage) / 100;
	}

	
	

	public  <T>T getBean(Class<T> type,String name)
	{
		return applicationContext.getBean(name, type);
	}
	
	
	
	
	public String currentDateOfferFormat(String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}
	
	public String currentDateTimeOfferFormat(String format)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}
	
	public LocalDate stringToDate(String dateString, String format)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return  LocalDate.parse(dateString, formatter);
	}
	
	public LocalDateTime stringToDateTime(String dateTimeString,String format)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		return  LocalDateTime.parse(dateTimeString, formatter);
	}
	
	public LocalDate stringToDate(String dateTimeString,int updatedYear,String format)
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		LocalDate date=  LocalDate.parse(dateTimeString, formatter);
		return date.withYear(updatedYear);
	}
	
	
	public String arrayToString(Object ob[])
	{
		return Arrays.stream(ob).map(Object::toString).collect(Collectors.joining(","));
	}
	
	
	
	
	
	
	
	

}
