package com.flyaway.admin.controller;

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

import com.flyaway.service.AdminService;

@WebServlet("/airportsServlet")
public class AirportsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static AdminService adminService = new AdminService();
       
    public AirportsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<List<String>> airportsList = new ArrayList<List<String>>();
			airportsList = adminService.getAllSourcesAndDestinations();
			if (airportsList.size() == 0) {
				RequestDispatcher rd = request.getRequestDispatcher("/AirportsError.html");
				rd.forward(request,  response);
			}
			if (airportsList.size() >= 1) {
				HttpSession session = request.getSession();
				session.setAttribute("allSources", airportsList.get(0));
				session.setAttribute("allDestinations", airportsList.get(1));
				RequestDispatcher rd = request.getRequestDispatcher("/AllAirports.jsp");
				rd.forward(request,  response);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
