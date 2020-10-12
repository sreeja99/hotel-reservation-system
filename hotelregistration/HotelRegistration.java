package com.capgemini.hotelregistration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelRegistration {
	private List<Hotel> hotelList =new ArrayList<Hotel>();//list of hotel
	//method to add hotel
	public boolean addHotel(String hotelName,int weekdayRegCustRate,int weekendRegCustRate) {
		Hotel hotel =new Hotel(hotelName,weekdayRegCustRate,weekendRegCustRate);
		hotelList.add(hotel);
		return true;
	}
	//finding cheapest hotel
	public Hotel findCheapestHotel(String start,String end) {
		Date StartDate=null;
		Date EndDate=null;
		 try {
			 StartDate = new SimpleDateFormat("dd-mm-yyyy").parse(start);
			 EndDate = new SimpleDateFormat("dd-mm-yyyy").parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 long noOfDays=1+(EndDate.getTime()-StartDate.getTime())/1000/60/60/24;
		 Hotel cheapestHotel = hotelList.stream().sorted(Comparator.comparing(Hotel::getWeekdayRegularCustRate)).findFirst().orElse(null);
		 long totalRate = noOfDays*cheapestHotel.getWeekdayRegularCustRate();
		 cheapestHotel.setTotalRate(totalRate);
		 return cheapestHotel; 
		 
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HotelRegistration hotelRegistration = new HotelRegistration();
		System.out.println("Welcome To Hotel Reservation Program in HotelReservation class");
		//adding hotels
		hotelRegistration.addHotel("Lakewood", 110,90);
		hotelRegistration.addHotel("Bridgewood", 160,60);
		hotelRegistration.addHotel("Ridgewood", 220,150);
		System.out.println("Do You Want To add a Hotel ?(Y/N)");
		char choice =sc.nextLine().charAt(0);
		if(choice=='Y') 
		{
		System.out.println("Enter the Hotel Name");
		String hotelName = sc.nextLine();
		System.out.println("Enter the  Weekday Regular Customer Rate");
		int weekdayRegCustRate = sc.nextInt();
		System.out.println("Enter the  Weekend Regular Customer Rate");
		int weekendRegCustRate = sc.nextInt();
		hotelRegistration.addHotel(hotelName,weekdayRegCustRate,weekendRegCustRate);
		}
		System.out.println("Enter the date range in ddmmyyyy format");
		System.out.println("Enter the start date:");
		String start =sc.nextLine();
		System.out.println("Enter the end date:");
		String end = sc.nextLine();
		Hotel cheapestHotel =hotelRegistration.findCheapestHotel(start, end);
		System.out.println(cheapestHotel.getHotelName()+",Total Wages :"+cheapestHotel.getTotalRate());
	}

}
