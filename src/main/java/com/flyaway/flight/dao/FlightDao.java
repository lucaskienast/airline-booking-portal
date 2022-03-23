package com.flyaway.flight.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import com.flyaway.db.DBConnection;
import com.flyaway.flight.bean.FlightBean;

public class FlightDao {
		
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FlightDao dao = new FlightDao();
		
		//dao.deleteFlightById(14);
		
		/*
		FlightBean bean = new FlightBean();
		bean.setFlightId(14);
		bean.setAirline("British Airways");
		bean.setDate("2022-01-04");
		bean.setSource("London");
		bean.setDestination("New York");
		bean.setTicketPriceUsd(599.99f);
		bean.setAvailableSeats(2);
		dao.insertFlight(bean);
		*/
		
		FlightBean bean = new FlightBean();
		bean.setFlightId(14);
		bean.setAirline("British Airways");
		bean.setDate("2022-01-04");
		bean.setSource("London");
		bean.setDestination("New York");
		bean.setTicketPriceUsd(599.99f);
		bean.setAvailableSeats(2);
		dao.updateFlightById(bean);
		
	}
	
	public List<FlightBean> getAllFlights() throws ClassNotFoundException, SQLException {
		
		List<FlightBean> listOfFlights = new ArrayList<FlightBean>();		
		Statement stmt = DBConnection.getConnection().createStatement();
		String query = "select * from flights";
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			FlightBean bean = new FlightBean();
			bean.setFlightId(rs.getInt(1));
			bean.setAirline(rs.getString(2));
			bean.setDate(rs.getString(3));
			bean.setSource(rs.getString(4));
			bean.setDestination(rs.getString(5));
			bean.setTicketPriceUsd(rs.getFloat(6));
			bean.setAvailableSeats(rs.getInt(7));
			listOfFlights.add(bean);
		}
		
		return listOfFlights;
	}
	
	public FlightBean getFlightById(int flightId) throws ClassNotFoundException, SQLException {
		
		FlightBean bean = new FlightBean();		
		Statement stmt = DBConnection.getConnection().createStatement();
		String query = "select * from flights where id=" + flightId;
		ResultSet rs = stmt.executeQuery(query);
		
		if (rs.next()) {
			bean.setFlightId(rs.getInt(1));
			bean.setAirline(rs.getString(2));
			bean.setDate(rs.getString(3));
			bean.setSource(rs.getString(4));
			bean.setDestination(rs.getString(5));
			bean.setTicketPriceUsd(rs.getFloat(6));
			bean.setAvailableSeats(rs.getInt(7));
		} else {
			bean = null;
		}
		
		return bean;
	}
	
	public int insertFlight(FlightBean bean) throws ClassNotFoundException, SQLException {
		
		String query = "insert into flights values (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		
		pstmt.setInt(1, bean.getFlightId());
		pstmt.setString(2, bean.getAirline());
		pstmt.setString(3, bean.getDate());
		pstmt.setString(4, bean.getSource());
		pstmt.setString(5, bean.getDestination());
		pstmt.setFloat(6, bean.getTicketPriceUsd());
		pstmt.setInt(7, bean.getAvailableSeats());
		
		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	
	public int deleteFlightById(int flightId) throws ClassNotFoundException, SQLException {
		
		String query = "delete from flights where id = ?";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		
		pstmt.setInt(1, flightId);
		
		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	
	public int updateFlightById(FlightBean bean) throws ClassNotFoundException, SQLException {

		String query = "update flights set available_seats=? where id = ?";
		PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(query);
		pstmt.setInt(1, bean.getAvailableSeats());
		pstmt.setInt(2, bean.getFlightId());

		int count = pstmt.executeUpdate();
		System.out.println("Rows affected: " + count);
		return count;
	}
	

}
