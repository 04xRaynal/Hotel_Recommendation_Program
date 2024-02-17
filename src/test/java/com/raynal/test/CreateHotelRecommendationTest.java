package com.raynal.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.raynal.bean.InputBean;
import com.raynal.enums.CustomerTypeEnum;
import com.raynal.enums.HotelNameEnum;
import com.raynal.exception.InvalidInputException;
import com.raynal.hotelRecommend.CreateHotelRecommendation;

public class CreateHotelRecommendationTest {
	final CreateHotelRecommendation hotelRecommendation = new CreateHotelRecommendation();
	
	@Test
	public void readInputFileTest_input1() {
		final InputBean result = hotelRecommendation.readInputFile("input1.json");
		assertEquals(CustomerTypeEnum.REGULAR.name(), result.getCustomer_type());
		assertEquals(3, result.getDates().size());
	}
	
	@Test
	public void readInputFileTest_input2() {
		final InputBean result = hotelRecommendation.readInputFile("input2.txt");
		assertEquals(CustomerTypeEnum.REWARD.name(), result.getCustomer_type());
		assertEquals(4, result.getDates().size());
	}
	
	@Test
	public void readInputFileTest_exceptionThrown() {
	    final Exception exception = assertThrows(InvalidInputException.class, () -> hotelRecommendation.readInputFile("input3.json"));
	    assertEquals("Error while reading the provided file data.", exception.getMessage());
	}
	
	@Test
	public void recommendHotel_regularCustomer() {
		final String result = hotelRecommendation.recommendHotel(createRegularCustomerInput());
		assertEquals(HotelNameEnum.RADISSON.name(), result);
	}
	
	@Test
	public void recommendHotel_rewardCustomer() {
		final String result = hotelRecommendation.recommendHotel(createRewardCustomerInput());
		assertEquals(HotelNameEnum.OYO.name(), result);
	}
	
	@Test
	public void recommendHotel_customerTypeException() {
		final InputBean bean = new InputBean();
		bean.setCustomer_type("NEW");
		
		final Exception exception = assertThrows(InvalidInputException.class, () -> hotelRecommendation.recommendHotel(bean));
	    assertEquals("Invalid customer_type attribute.", exception.getMessage());
	}
	
	@Test
	public void recommendHotel_datesException() {
		final InputBean bean = new InputBean();
		bean.setCustomer_type(CustomerTypeEnum.REGULAR.name());
		bean.setDates(Arrays.asList("18/04/2021"));
		
		final Exception exception = assertThrows(InvalidInputException.class, () -> hotelRecommendation.recommendHotel(bean));
	    assertEquals("Invalid dates attribute.", exception.getMessage());
	}
	
	private InputBean createRegularCustomerInput() {
		final List<String> dateList = Arrays.asList("18 May 2021", "19 May 2021", "21 May 2021");
		
		final InputBean bean = new InputBean();
		bean.setCustomer_type(CustomerTypeEnum.REGULAR.name());
		bean.setDates(dateList);
		return bean;
	}
	
	private InputBean createRewardCustomerInput() {
		final List<String> dateList = Arrays.asList("19 May 2021", "20 May 2021", "21 May 2021", "22 May 2021");
		
		final InputBean bean = new InputBean();
		bean.setCustomer_type(CustomerTypeEnum.REWARD.name());
		bean.setDates(dateList);
		return bean;
	}
}
