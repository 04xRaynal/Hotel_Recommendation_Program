package com.raynal.hotel;

import com.raynal.enums.HotelNameEnum;
import com.raynal.interfaces.Hotel;

public class Taj implements Hotel {
	private static final HotelNameEnum HOTEL_NAME = HotelNameEnum.TAJ;
	
	private static final int REGULAR_CHARGES_WEEKDAY = 120;
	private static final int REGULAR_CHARGES_WEEKEND = 110;
	
	private static final int REWARD_CHARGES_WEEKDAY = 105;
	private static final int REWARD_CHARGES_WEEKEND = 95;
	
	private static final int RATING_POINTS = 2;
	
	private Integer totalCost;
	
	@Override
	public HotelNameEnum getHotelName() {
		return HOTEL_NAME;
	}
	
	@Override
	public int getRegularWeekdayCharges() {
		return REGULAR_CHARGES_WEEKDAY;
	}
	
	@Override
	public int getRegularWeekendCharges() {
		return REGULAR_CHARGES_WEEKEND;
	}
	
	@Override
	public int getRewardWeekdayCharges() {
		return REWARD_CHARGES_WEEKDAY;
	}
	
	@Override
	public int getRewardWeekendCharges() {
		return REWARD_CHARGES_WEEKEND;
	}
	
	@Override
	public int getRatingPoints() {
		return RATING_POINTS;
	}
	
	@Override
	public Integer getTotalCost() {
		return totalCost;
	}
	
	@Override
	public void setTotalCost(final Integer totalCost) {
		this.totalCost = totalCost;
	}
}
