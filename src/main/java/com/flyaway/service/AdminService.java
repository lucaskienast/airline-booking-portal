package com.flyaway.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.flyaway.auth.bean.AuthenticationBean;
import com.flyaway.auth.dao.AuthenticationDao;
import com.flyaway.flight.bean.FlightBean;
import com.flyaway.flight.dao.FlightDao;

public class AdminService {
	
	FlightDao flightDao = new FlightDao();
	AuthenticationDao authDao = new AuthenticationDao();
	/*
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AdminService adminServ = new AdminService();
		
		AuthenticationBean bean = new AuthenticationBean();
		bean.setEmail("admin@flyaway.com");
		bean.setPassword("secret");
	
		System.out.println(adminServ.login(bean));
	}
	 */

	public String login(AuthenticationBean beanFromUser) throws ClassNotFoundException, SQLException {
		String isAdmin = null;
		AuthenticationBean beanFromDb = authDao.getUserByEmail(beanFromUser.getEmail());
		if (null != beanFromDb) {
			if (beanFromUser.getEmail().equals(beanFromDb.getEmail()) && beanFromUser.getPassword().equals(beanFromDb.getPassword())) {
				if (beanFromDb.isAdmin() == true) {
					isAdmin = "admin";
				}
				if (beanFromDb.isAdmin() == false) {
					isAdmin = "user";
				}
			}
		}
		return isAdmin;
	}
	
	public void logout() {
		
	}
	
	public int changePassword(AuthenticationBean bean) throws ClassNotFoundException, SQLException {
		int rowsAffected = authDao.updateUserById(bean);
		return rowsAffected;
	}
	
	public List<List<String>> getAllSourcesAndDestinations() throws ClassNotFoundException, SQLException {
		List<FlightBean> flightsList = flightDao.getAllFlights();
		List<List<String>> airportsLists = new ArrayList<List<String>>();
		List<String> sourcesList = new ArrayList<String>();
		List<String> destinationsList = new ArrayList<String>();
		for (FlightBean bean : flightsList) {
			if (!sourcesList.contains(bean.getSource())) {
				sourcesList.add(bean.getSource());
				System.out.println(bean.getSource());
			}
			if (!destinationsList.contains(bean.getDestination())) {
				destinationsList.add(bean.getDestination());
				System.out.println(bean.getDestination());
			}
		}
		System.out.println(sourcesList.size());
		System.out.println(destinationsList.size());
		airportsLists.add(sourcesList);
		airportsLists.add(destinationsList);
		return airportsLists;
	}
	
	public List<String> getAllAirlines() throws ClassNotFoundException, SQLException {
		List<FlightBean> flightsList = flightDao.getAllFlights();
		List<String> airlinesList = new ArrayList<String>();
		for (FlightBean bean : flightsList) {
			if (!airlinesList.contains(bean.getAirline())) {
				airlinesList.add(bean.getAirline());
			}
		}
		System.out.println(airlinesList.size());
		return airlinesList;
	}
	
	public List<FlightBean> getAllFlights() throws ClassNotFoundException, SQLException {
		List<FlightBean> flightsList = flightDao.getAllFlights();
		System.out.println(flightsList.size());
		return flightsList;
	}

}
