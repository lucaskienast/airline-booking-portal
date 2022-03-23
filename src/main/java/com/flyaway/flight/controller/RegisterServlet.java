package com.flyaway.flight.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flyaway.auth.bean.AuthenticationBean;
import com.flyaway.service.FlightService;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static FlightService flightService = new FlightService();
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String password = request.getParameter("password");
		AuthenticationBean beanFromUser = new AuthenticationBean();
		beanFromUser.setEmail(email);
		beanFromUser.setName(name);
		beanFromUser.setGender(gender);
		beanFromUser.setPassword(password);
		int rowsAffected = 0;
		if (email.length() != 0 && name.length() != 0 && gender.length() != 0 && password.length() != 0) {
			rowsAffected = flightService.registerPassenger(beanFromUser);
		}
		System.out.println(rowsAffected);
		if (rowsAffected >= 1) {
			HttpSession session = request.getSession();
			session.setAttribute("customerBean", beanFromUser);
			RequestDispatcher rd = request.getRequestDispatcher("/Overview.jsp");
			rd.forward(request,  response);
		} else{ 
			RequestDispatcher rd = request.getRequestDispatcher("/RegisterError.html");
			rd.forward(request,  response);
		}
	}

}
