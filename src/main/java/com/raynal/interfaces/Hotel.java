package com.raynal.interfaces;

import com.raynal.enums.HotelNameEnum;

public interface Hotel {
	HotelNameEnum getHotelName();
	
	int getRegularWeekdayCharges();
	int getRegularWeekendCharges();
	
	int getRewardWeekdayCharges();
	int getRewardWeekendCharges();
	
	int getRatingPoints();
	
	Integer getTotalCost();
	void setTotalCost(final Integer totalCost);
}
