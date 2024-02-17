package com.raynal.hotelRecommend;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Optional;

import com.raynal.comparator.LowestCostHotelComparator;
import com.raynal.enums.HotelNameEnum;
import com.raynal.exception.InvalidInputException;
import com.raynal.interfaces.Hotel;

public class CheapestHotel {
	public static int getHotelRegularChargeByDayOfWeek(final Hotel hotel, final DayOfWeek dayOfWeek) {
		switch(dayOfWeek) {
			case MONDAY:
			case TUESDAY:
			case WEDNESDAY:
			case THURSDAY:
			case FRIDAY:
				return hotel.getRegularWeekdayCharges();
			case SATURDAY:
			case SUNDAY:
				return hotel.getRegularWeekendCharges();
			default:
				throw new InvalidInputException("Invalid DayOfWeek");
		}
	}
	
	public static int getHotelRewardChargeByDayOfWeek(final Hotel hotel, final DayOfWeek dayOfWeek) {
		switch(dayOfWeek) {
			case MONDAY:
			case TUESDAY:
			case WEDNESDAY:
			case THURSDAY:
			case FRIDAY:
				return hotel.getRewardWeekdayCharges();
			case SATURDAY:
			case SUNDAY:
				return hotel.getRewardWeekendCharges();
			default:
				throw new InvalidInputException("Invalid DayOfWeek");
		}
	}
	
	public static HotelNameEnum compareHotelCharges(final Hotel... hotels) {
		final Optional<Hotel> lowestCostHotel = Arrays.stream(hotels)
													.max(new LowestCostHotelComparator());
		
		if(lowestCostHotel.isEmpty())
			throw new InvalidInputException("Unable to find Cheapest hotel for the provided input!");
			
		return lowestCostHotel.get().getHotelName();
	}
}
