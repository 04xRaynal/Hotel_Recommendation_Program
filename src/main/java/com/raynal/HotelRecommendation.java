package com.raynal;

import java.util.Scanner;

import com.raynal.bean.InputBean;
import com.raynal.exception.InvalidInputException;
import com.raynal.hotelRecommend.CreateHotelRecommendation;

public class HotelRecommendation {
	public static void main(String[] args) {
		System.out.println("Enter the file name (with extension) containing input details:");
		final Scanner sc = new Scanner(System.in);
		
		try {
			final String fileName = sc.nextLine();
			
			final CreateHotelRecommendation hotelRecommendation = new CreateHotelRecommendation();
			final InputBean inputBean = hotelRecommendation.readInputFile(fileName);
			
			final String hotelName = hotelRecommendation.recommendHotel(inputBean);
			System.out.println("Cheapest Hotel would be: " + hotelName);
		}
		catch(InvalidInputException ex) {
			System.out.println(ex.getMessage());
		}
		catch(Exception ex) {
			System.out.println("Error while recommending hotel! Please check input and try again.");
		}
		finally {
			sc.close();
		}
	}
}
