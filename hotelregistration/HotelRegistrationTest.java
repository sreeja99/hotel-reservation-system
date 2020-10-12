package com.capgemini.hotelregistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class HotelRegistrationTest {
	//testing hotel addition
	@Test
	public void whenHotelIsAddedShouldReturnTrue() {
		HotelRegistration hotelRegistration =new HotelRegistration ();
		boolean isHotelAdded = hotelRegistration.addHotel("Lakewood", 110,90,3);
		Assert.assertEquals(true, isHotelAdded);
	}
	@Test
	public void forGivenDatesItShouldReturnLAkewood() {
		HotelRegistration.addHotel("Lakewood", 110, 90, 3);
		HotelRegistration.addHotel("Bridgewood", 160, 60, 4);
		HotelRegistration.addHotel("Ridgewood", 220, 150, 5);
	    Hotel hotel = HotelRegistration.findCheapestHotel("01-Oct-2020", "06-Oct-2020");
	    assertEquals("Lakewood", hotel.getHotelName());
	}

}
