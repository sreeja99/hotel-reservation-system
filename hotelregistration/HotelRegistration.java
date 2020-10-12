package com.capgemini.hotelregistration;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelRegistration {
	private List<Hotel> hotelList =new ArrayList<Hotel>();//list of hotels
	//method to add hotel
	public boolean addHotel(String hotelName,int regCustRate) {
		Hotel hotel =new Hotel(hotelName,regCustRate);
		hotelList.add(hotel);
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HotelRegistration hotelRegistration = new HotelRegistration();
		System.out.println("Welcome To Hotel Reservation Program in HotelReservation class");
		System.out.println("Enter the Hotel Name");
		String hotelName = sc.nextLine();
		System.out.println("Enter the Regular Customer Rate");
		int regCustRate = sc.nextInt();
		hotelRegistration.addHotel(hotelName, regCustRate);
		
	}

}
