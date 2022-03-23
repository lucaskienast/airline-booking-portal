package com.flyaway.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyaway.flight.bean.FlightBean;
import com.flyaway.auth.bean.AuthenticationBean;
import com.flyaway.auth.dao.AuthenticationDao;
import com.flyaway.flight.dao.FlightDao;

public class FlightService {
	
	FlightDao flightDao = new FlightDao();
	AuthenticationDao authDao = new AuthenticationDao();

	/*
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FlightService fs = new FlightService();
		
		FlightBean bean = new FlightBean();
		bean.setFlightId(13);
		bean.setAvailableSeats(77);
		//bean.setDate("2022-01-04");
		//bean.setSource("London");
		//bean.setDestination("New York");
		
		fs.bookSeatsOnFlight(bean, 1);
	}
	*/
	
	public List<FlightBean> getFilteredFlights(FlightBean bean, int wantedSeats) throws ClassNotFoundException, SQLException {
		// get all flights that match date, source, and destination
		// and offer enough seats
		List<FlightBean> flightsList = flightDao.getAllFlights();
		List<FlightBean> filteredList = new ArrayList<FlightBean>();
		for (FlightBean b : flightsList) {
			if (b.getDate().equals(bean.getDate())
					&& b.getSource().equals(bean.getSource())
					&& b.getDestination().equals(bean.getDestination())
					&& enoughSeats(b.getAvailableSeats(), wantedSeats)) {
				filteredList.add(b);
			}
		}
		System.out.println(filteredList.size());
		return filteredList;
	}
	
	public int registerPassenger(AuthenticationBean bean) {
		int rowsAffected = 0;
		try {
			rowsAffected = authDao.insertUser(bean);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(rowsAffected);
		return rowsAffected;
	}
	
	public int bookSeatsOnFlight(FlightBean bean, int wantedSeats) throws ClassNotFoundException, SQLException {
		// only reduces number of available tickets by the amount booked
		int updatedRows = 0;
		FlightBean updatedFlightBean = bean;
		updatedFlightBean.setAvailableSeats(
				(enoughSeats(updatedFlightBean.getAvailableSeats(), wantedSeats))
				? updatedFlightBean.getAvailableSeats()-wantedSeats
				: 0);
		updatedRows = flightDao.updateFlightById(updatedFlightBean);
		System.out.println(updatedRows);
		return updatedRows;
	}
	
	private boolean enoughSeats(int availableSeats, int wantedSeats) {
		if (availableSeats >= wantedSeats) {
			return true;
		}
		return false;
	}

}
