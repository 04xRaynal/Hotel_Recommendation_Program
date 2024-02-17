package com.raynal.interfaces;

import com.raynal.enums.HotelNameEnum;

public interface HotelFactory {
	Hotel getHotel(final HotelNameEnum hotelName);
}
