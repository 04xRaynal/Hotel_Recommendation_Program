package com.raynal.comparator;

import java.util.Comparator;

import com.raynal.interfaces.Hotel;

public class LowestCostHotelComparator implements Comparator<Hotel> {
	@Override
	public int compare(final Hotel firstHotel, final Hotel secondHotel) {
		final int lowestTotalCost = secondHotel.getTotalCost().compareTo(firstHotel.getTotalCost());
		
		if(lowestTotalCost == 0)
			return firstHotel.getRatingPoints() - secondHotel.getRatingPoints();
		
		return lowestTotalCost;
	}
}
