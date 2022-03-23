package com.flyaway.flight.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/paymentServlet")
public class PaymentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	       
    public PaymentServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cardNumber = request.getParameter("cardNumber");
		String nameOnCard = request.getParameter("nameOnCard");
		String cardExpiryDate = request.getParameter("cardExpiryDate");
		String cardCode = request.getParameter("cardCode");
		if (cardNumber.length() != 0 && nameOnCard.length() != 0 && cardExpiryDate.length() != 0 && cardCode.length() != 0) {
			HttpSession session = request.getSession();
			RequestDispatcher rd = request.getRequestDispatcher("/BookingSuccess.jsp");
			rd.forward(request,  response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/PaymentError.html");
			rd.forward(request,  response);
		}
	}

}
