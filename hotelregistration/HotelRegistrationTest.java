package com.capgemini.hotelregistration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class HotelRegistrationTest {
	//testing hotel addition
	@Test
	public void whenHotelIsAddedShouldReturnTrue() {
		HotelRegistration hotelRegistration =new HotelRegistration ();
		boolean isHotelAdded = hotelRegistration.addHotel("Lakewood", 100);
		Assert.assertEquals(true, isHotelAdded);
	}

}
