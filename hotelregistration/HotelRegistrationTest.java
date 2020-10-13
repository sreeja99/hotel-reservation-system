package com.capgemini.hotelregistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class HotelRegistrationTest {
	HotelRegistration hotelRegistration;
	Customer customer;
	//testing hotel addition
	@Test
	public void whenHotelIsAddedShouldReturnTrue() {
		HotelRegistration hotelRegistration =new HotelRegistration ();
		boolean isHotelAdded = hotelRegistration.addHotel("Lakewood", 110,90,3,50,20);
		Assert.assertEquals(true, isHotelAdded);
	}
	//testing for cheapest hotel
	@Test
	public void forGivenDatesItShouldReturnLakewoodAsCheapest() {
		customer.setCustomerType("regular");
		HotelRegistration.addHotel("Lakewood", 110, 90, 3,150,110);
		HotelRegistration.addHotel("Bridgewood", 160, 60, 4,110,34);
		HotelRegistration.addHotel("Ridgewood", 220, 150, 5,200,110);
	    Hotel hotel = HotelRegistration.findCheapestBestRatedHotel("01-Oct-2020", "06-Oct-2020",customer);
	    assertEquals("Lakewood", hotel.getHotelName());
	}
	//testing cheapest best rated hotel
	@Test
	public void forGivenDatesItshouldReturnCheapestBestRatedHotel() {
		customer.setCustomerType("regular");
		HotelRegistration.addHotel("Lakewood", 110, 90, 3,200,140);
		HotelRegistration.addHotel("Bridgewood", 160, 60, 4,100,50);
		HotelRegistration.addHotel("Ridgewood", 220, 150, 5,150,30);
	    Hotel hotel = HotelRegistration.findCheapestBestRatedHotel("01-Oct-2020", "06-Oct-2020",customer);
	    assertEquals("Cheapest Hotel", hotel.getHotelName());
	}
	//testing best rated hotel
	@Test
	public void returnBestRatedHotel() {
		HotelRegistration.addHotel("Lakewood", 110, 90, 3,100,30);
		HotelRegistration.addHotel("Bridgewood", 160, 60, 4,30,10);
		HotelRegistration.addHotel("Ridgewood", 220, 150, 5,80,20);
	    Hotel hotel = hotelRegistration.findBestRatedHotel("01-Oct-2020", "06-Oct-2020",customer);
	    assertEquals("Best Rated Hotel", hotel.getHotelName());
	}
	//testing cheapest best rated htel for regular customer type
	@Test
	public void whenCustomerIsRegularReturnBestRatedHotel() {
		customer.setCustomerType("regular");
		HotelRegistration.addHotel("Best Rated Hotel", 200, 150, 6,50,20);
		Hotel hotel = hotelRegistration.findBestRatedHotel("01-Oct-2020", "06-Oct-2020",customer);
		assertEquals("Best Rated Hotel", hotel.getHotelName());
	}
	//testing cheapest best rated for reward customer type
	@Test
	public void whenCustomerIsRewardFindCheapestBestRatedHotelAndReturnReqwardCustRate() {
		customer.setCustomerType("reward");
		Hotel hotel = hotelRegistration.findBestRatedHotel("01-Oct-2020", "06-Oct-2020",customer);
		assertEquals(480, hotel.getTotalRate());
	}

}
