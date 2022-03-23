package com.flyaway.flight.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.flight.bean.FlightBean;
import com.flyaway.service.FlightService;

@WebServlet("/flightServlet")
public class FlightServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static FlightService flightService = new FlightService();
       
    public FlightServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String date = request.getParameter("date");
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		String wantedSeatsString = request.getParameter("wantedSeats");
		int wantedSeats = (wantedSeatsString.length() >= 1) 
				? Integer.parseInt(wantedSeatsString)
				: 0;
		System.out.println(wantedSeats);
		FlightBean beanFromUser = new FlightBean();
		beanFromUser.setDate(date);
		beanFromUser.setSource(source);
		beanFromUser.setDestination(destination);
		
		if (date != null && source != null && destination != null && wantedSeats > 0) {
			try {
				List<FlightBean> filteredList = new ArrayList<FlightBean>();
				filteredList = flightService.getFilteredFlights(beanFromUser, wantedSeats);
				if (filteredList.size() == 0) {
					RequestDispatcher rd = request.getRequestDispatcher("/FlightsError.html");
					rd.forward(request,  response);
				}
				if (filteredList.size() >= 1) {
					HttpSession session = request.getSession();
					session.setAttribute("wantedSeats", wantedSeats);
					session.setAttribute("filteredFlights", filteredList);
					RequestDispatcher rd = request.getRequestDispatcher("/FilteredFlights.jsp");
					rd.forward(request,  response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/FlightsHome.html");
			rd.forward(request,  response);
		}
		
	}

}
