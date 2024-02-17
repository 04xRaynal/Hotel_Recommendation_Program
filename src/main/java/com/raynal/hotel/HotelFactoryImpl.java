package com.raynal.hotel;

import com.raynal.enums.HotelNameEnum;
import com.raynal.exception.InvalidInputException;
import com.raynal.interfaces.Hotel;
import com.raynal.interfaces.HotelFactory;

public class HotelFactoryImpl implements HotelFactory {
	@Override
	public Hotel getHotel(final HotelNameEnum hotelName) {
		switch(hotelName) {
			case RADISSON:
				return new Radisson();
			case TAJ:
				return new Taj();
			case OYO:
				return new Oyo();
			default:
				throw new InvalidInputException("Invalid Hotel name.");
		}
	}
}
