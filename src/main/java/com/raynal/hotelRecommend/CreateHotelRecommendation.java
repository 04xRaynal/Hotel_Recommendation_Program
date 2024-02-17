package com.raynal.hotelRecommend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raynal.bean.InputBean;
import com.raynal.enums.CustomerTypeEnum;
import com.raynal.enums.HotelNameEnum;
import com.raynal.exception.InvalidInputException;
import com.raynal.hotel.HotelFactoryImpl;
import com.raynal.interfaces.Hotel;
import com.raynal.interfaces.HotelFactory;

public class CreateHotelRecommendation {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
	
	public InputBean readInputFile(final String fileName) {
		try {
			final Path filePath = Paths.get(".\\src\\main\\resources\\" + fileName);
			final byte[] fileData = Files.readAllBytes(filePath);
			
			final ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(fileData, InputBean.class);
		}
		catch(InvalidPathException ex) {
			throw new InvalidInputException("Error while reading file from the provided file path.");
		}
		catch(IOException ex) {
			throw new InvalidInputException("Error while reading the provided file data.");
		}
	}
	
	public String recommendHotel(final InputBean inputBean) {
		try {
			final CustomerTypeEnum consumerType = CustomerTypeEnum.valueOf(inputBean.getCustomer_type());
			
			final List<LocalDate> inputDatesList = inputBean.getDates()
															.stream()
															.map(dateStr -> LocalDate.parse(dateStr, formatter))
															.collect(Collectors.toList());
			
			return findCheapestHotel(consumerType, inputDatesList);
		}
		catch(IllegalArgumentException ex) {
			throw new InvalidInputException("Invalid customer_type attribute.");
		}
		catch(DateTimeParseException ex) {
			throw new InvalidInputException("Invalid dates attribute.");
		}
	}
	
	private String findCheapestHotel(final CustomerTypeEnum customerType, final List<LocalDate> dates) {
		final List<DayOfWeek> dayOfWeekList = dates.stream()
													.map(LocalDate::getDayOfWeek)
													.collect(Collectors.toList());
		
		return getCheapestHotelByCustomerType(customerType, dayOfWeekList);
	}
	
	private String getCheapestHotelByCustomerType(final CustomerTypeEnum customerType, final List<DayOfWeek> daysOfWeek) {
		final HotelFactory factory = new HotelFactoryImpl();
		
		final Hotel radisson = factory.getHotel(HotelNameEnum.RADISSON);
		final Integer radissonTotalCost = getHotelTotalCost(radisson, customerType, daysOfWeek);
		radisson.setTotalCost(radissonTotalCost);
		
		final Hotel taj = factory.getHotel(HotelNameEnum.TAJ);
		final Integer tajCharge = getHotelTotalCost(taj, customerType, daysOfWeek);
		taj.setTotalCost(tajCharge);
		
		final Hotel oyo = factory.getHotel(HotelNameEnum.OYO);
		final Integer oyoCharge = getHotelTotalCost(oyo, customerType, daysOfWeek);
		oyo.setTotalCost(oyoCharge);
		
		final HotelNameEnum cheapestHotelName = CheapestHotel.compareHotelCharges(radisson, taj, oyo);
		return cheapestHotelName.name();
	}
	
	private Integer getHotelTotalCost(final Hotel hotel, final CustomerTypeEnum customerType, final List<DayOfWeek> daysOfWeek) {
		return daysOfWeek.stream()
						.map(dayOfWeek -> getCheapestHotelChargeByCustomerType(hotel, customerType, dayOfWeek))
						.reduce(0, Integer::sum);
	}
	
	private Integer getCheapestHotelChargeByCustomerType(final Hotel hotel, final CustomerTypeEnum customerType, final DayOfWeek dayOfWeek) {
		if(customerType == CustomerTypeEnum.REGULAR)
			return CheapestHotel.getHotelRegularChargeByDayOfWeek(hotel, dayOfWeek);
		
		return CheapestHotel.getHotelRewardChargeByDayOfWeek(hotel, dayOfWeek);
	}
}
