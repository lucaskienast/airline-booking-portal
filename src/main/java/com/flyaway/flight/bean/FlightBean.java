package com.flyaway.flight.bean;

public class FlightBean {
	
	private int flightId;
	private String airline;
	private String date;
	private String source;
	private String destination;
	private float ticketPriceUsd;
	private int availableSeats;
	
	
	public FlightBean() {
	}
	public FlightBean(String airline, String date, String source, String destination, int ticketPriceUsd,
			int availableSeats) {
		this.airline = airline;
		this.date = date;
		this.source = source;
		this.destination = destination;
		this.ticketPriceUsd = ticketPriceUsd;
		this.availableSeats = availableSeats;
	}
	public int getFlightId() {
		return flightId;
	}
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public float getTicketPriceUsd() {
		return ticketPriceUsd;
	}
	public void setTicketPriceUsd(float ticketPriceUsd) {
		this.ticketPriceUsd = ticketPriceUsd;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
