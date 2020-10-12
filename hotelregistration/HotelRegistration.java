package com.capgemini.hotelregistration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HotelRegistration {
	private static List<Hotel> hotelList =new ArrayList<Hotel>();//list of hotel
	//method to add hotel
	public static boolean addHotel(String hotelName,int weekdayRegCustRate,int weekendRegCustRate,int rating) {
		Hotel hotel =new Hotel(hotelName,weekdayRegCustRate,weekendRegCustRate,rating);
		hotelList.add(hotel);
		return true;
	}
	//finding cheapest hotel
	public static Hotel findCheapestHotel(String start,String end) {
		Date StartDate=null;
		Date EndDate=null;
		 try {
			 StartDate = new SimpleDateFormat("dd-mm-yyyy").parse(start);
			 EndDate = new SimpleDateFormat("dd-mm-yyyy").parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 long noOfDays=1+(EndDate.getTime()-StartDate.getTime())/1000/60/60/24;
		  Calendar startCal = Calendar.getInstance();
	      startCal.setTime(StartDate);  
		  Calendar endCal = Calendar.getInstance();
	      endCal.setTime(EndDate);
	      long noOfWeekdays = 0;
	      if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
	            startCal.setTime(EndDate);
	            endCal.setTime(StartDate);
	            
	        }
	        do {
	            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	                ++noOfWeekdays;
	            }
	            startCal.add(Calendar.DAY_OF_MONTH, 1);
	        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); 
	        
	       long noOfWeekends = noOfDays - noOfWeekdays;
	       System.out.println("Weekends :"+ noOfWeekends +"Weekdays :"+noOfWeekdays);
	        
	       for(Hotel hotel: hotelList) {
	        	long totalRate = noOfWeekdays*hotel.getWeekdayRegularCustRate()+noOfWeekends*hotel.getWeekendRegularCustRate();
	        	hotel.setTotalRate(totalRate);
	        }
		 Hotel cheapestHotel = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).findFirst().orElse(null);
		 return cheapestHotel; 
		 
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		HotelRegistration hotelRegistration = new HotelRegistration();
		System.out.println("Welcome To Hotel Reservation Program in HotelReservation class");
		//adding hotels
		hotelRegistration.addHotel("Lakewood", 110,90,3);
		hotelRegistration.addHotel("Bridgewood", 160,60,4);
		hotelRegistration.addHotel("Ridgewood", 220,150,5);
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
		System.out.println("Enter The rating of hotel");
		int rating=sc.nextInt();
		hotelRegistration.addHotel(hotelName,weekdayRegCustRate,weekendRegCustRate,rating);
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
