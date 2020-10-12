package com.capgemini.hotelregistration;

public class Hotel {
	private int weekdayRegCustRate;
	private int weekendRegCustRate;
	private String hotelName;
	private long totalRate;
	private int rating;
	//constructor
	public Hotel(String hotelName,int weekdayRegCustRate,int weekendRegCustRate,int rating) {
		this.hotelName = hotelName;
		this.rating = rating;
		this.weekdayRegCustRate=weekdayRegCustRate;
		this.weekendRegCustRate=weekendRegCustRate;
	}
	//getters and setters
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setWeekdayRegularCustRate(int weekdayRegCustRate) {
		this.weekdayRegCustRate=weekdayRegCustRate;
	}
	public int getWeekdayRegularCustRate() {
		return weekdayRegCustRate;
	}
	public void setWeekendRegularCustRate(int weekendRegCustRate) {
		this.weekendRegCustRate=weekendRegCustRate;
	}
	public int getWeekendRegularCustRate() {
		return weekendRegCustRate;
	}
	public long getTotalRate() {
		return totalRate;
	}
	public void setRating(int rating) {
		this.rating=rating;
	}
	public int getRating() {
		return rating;
	}
	public void setTotalRate(long totalRate) {
		this.totalRate = totalRate;
	}
}
