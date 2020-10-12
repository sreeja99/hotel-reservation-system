package com.capgemini.hotelregistration;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HotelRegistration {
	private static List<Hotel> hotelList =new ArrayList<Hotel>();//list of hotel
	//method to add hotel
	public static boolean addHotel(String hotelName,int weekdayRegCustRate,int weekendRegCustRate,int rating) {
		Hotel hotel =new Hotel(hotelName,weekdayRegCustRate,weekendRegCustRate,rating);
		hotelList.add(hotel);
		return true;
	}
	public static Date parseDate(String date) {
        try {
			return new SimpleDateFormat("dd-MMM-yyyy").parse(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	//getting total number of days
	public static long getNoOfDays(String startD,String endD) {
		Date startDate=null;
        Date endDate = null;
        startDate = parseDate(startD);
        endDate = parseDate(endD); 
        if(startDate.getTime()<endDate.getTime()) {
	        long noOfDays = 1+(endDate.getTime()- startDate.getTime())/1000/60/60/24;
			return noOfDays;
        }
        else 
        	return 0;
	}
	//number of weekdays
	public static long getNoOfWeekdays(String start, String end) {
		Date startDate=null;
        Date endDate = null;
        startDate = parseDate(start);
        endDate = parseDate(end); 
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);        

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        long noOfWeekdays = 0;
        if (startCal.getTimeInMillis() < endCal.getTimeInMillis()) {
        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++noOfWeekdays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()); 
        return noOfWeekdays;
        }
        else 
        	return 0;
	}
	//totalrate including weekdays and weekends
	public static void setTotalRateForHotels(long noOfWeekdays,long noOfWeekends) {
		for(Hotel hotel: hotelList) {
        	long totalRate = noOfWeekdays*hotel.getWeekdayRegularCustRate()+noOfWeekends*hotel.getWeekendRegularCustRate();
        	hotel.setTotalRate(totalRate);
		}
	}
	//cheapest best rated hotel
	public static Hotel findCheapestBestRatedHotel(String startD, String endD) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
        long noOfWeekends = noOfDays - noOfWeekdays;
        
        if(noOfDays>0){
        	setTotalRateForHotels(noOfWeekdays,noOfWeekends);
	        List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getTotalRate)).collect(Collectors.toList());
	        
	        Hotel cheapestHotel = sortedHotelList.get(0);
	        long cheapestRate= sortedHotelList.get(0).getTotalRate();
	        for(Hotel hotel:sortedHotelList) {
	        	if(hotel.getTotalRate()<=cheapestRate) {
	        		if(hotel.getRating()>cheapestHotel.getRating())
	        			cheapestHotel = hotel;
	        	}
	        	else 
	        		break;
	        }
			return cheapestHotel;
	    }
        else
        	return null;
	}
	//best rated hotel
	public static Hotel findBestRatedHotel(String startD, String endD) {
		long noOfWeekdays = getNoOfWeekdays(startD, endD);
		long noOfDays = getNoOfDays(startD, endD);
        long noOfWeekends = noOfDays - noOfWeekdays;
        
        if(noOfDays>0){
        	setTotalRateForHotels(noOfWeekdays,noOfWeekends);
	        List<Hotel> sortedHotelList = hotelList.stream().sorted(Comparator.comparing(Hotel::getRating).reversed()).collect(Collectors.toList());
	        Hotel bestRatedHotel = sortedHotelList.get(0);
	        int bestHotelRating= sortedHotelList.get(0).getRating();
	        for(Hotel hotel:sortedHotelList) {
	        	if(hotel.getRating()>=bestHotelRating) {
	        		if(hotel.getTotalRate()<bestRatedHotel.getTotalRate())
	        			bestRatedHotel = hotel;
	        	}
	        	else 
	        		break;
	        }
			return bestRatedHotel;
	    }
        else
        	return null;
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
		Hotel cheapestHotel =hotelRegistration.findCheapestBestRatedHotel(start, end);
		Hotel bestRatedHotel = hotelRegistration.findBestRatedHotel(start,end);
		System.out.println(cheapestHotel.getHotelName()+",Total Wages :"+cheapestHotel.getTotalRate()+"rating:"+cheapestHotel.getRating());
		System.out.println(bestRatedHotel.getHotelName()+", Rating: "+bestRatedHotel.getRating()+", Total rate :$"+bestRatedHotel.getTotalRate());
	}

}
