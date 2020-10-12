package com.capgemini.hotelregistration;

public class Hotel {
	private int regCustRate;
	private String hotelName;
	private long totalRate;
	//constructor
	public Hotel(String hotelName, int regCustRate) {
		this.hotelName = hotelName;
		this.regCustRate = regCustRate;
	}
	//getters and setters
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setRegularCustRate(int regCustRate) {
		this.regCustRate=regCustRate;
	}
	public int getRegularCustRate() {
		return regCustRate;
	}
	public long getTotalRate() {
		return totalRate;
	}

	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
}
