package com.raynal.hotel;

import com.raynal.enums.HotelNameEnum;
import com.raynal.interfaces.Hotel;

public class Oyo implements Hotel {
	private static final HotelNameEnum HOTEL_NAME = HotelNameEnum.OYO;
	
	private static final int REGULAR_CHARGES_WEEKDAY = 130;
	private static final int REGULAR_CHARGES_WEEKEND = 90;
	
	private static final int REWARD_CHARGES_WEEKDAY = 90;
	private static final int REWARD_CHARGES_WEEKEND = 80;
	
	private static final int RATING_POINTS = 4;
	
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
