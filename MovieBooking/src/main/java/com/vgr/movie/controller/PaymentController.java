package com.vgr.movie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vgr.movie.dao.CustomerDAO;
import com.vgr.movie.dao.MovieDAO;
import com.vgr.movie.dao.MovieTicketDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Customer;
import com.vgr.movie.pojo.Movie;
import com.vgr.movie.pojo.Payment;



@Controller
@RequestMapping(value = "/payment*.htm")
public class PaymentController {

	@Autowired
	CustomerDAO pdao;

	@Autowired
	PaymentValidator validator;

	@Autowired
	MovieTicketDAO tdao;

	@Autowired
	MovieDAO mdao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	
	@RequestMapping(value = "/payment.htm", method = RequestMethod.GET)
	public String initialize(@ModelAttribute("payment") Payment payment) {
		
		
		return "payment";
	}

	@RequestMapping(value = "/payment.htm", method = RequestMethod.POST)
	public String addPayment(HttpServletRequest request, HttpServletResponse response) throws MovieException {

		HttpSession session = request.getSession();
		long customer_id = ((Long) session.getAttribute("customer_id"));

		try {

			String number = request.getParameter("creditCardNumber");
			number = number.replaceAll("[^0-9]", "");
			long creditCardNumber = Long.parseLong(number);
			
			String bankName = request.getParameter("bankName");
			bankName = bankName.replaceAll("[^\\dA-Za-z]", "");
			String fullName = request.getParameter("fullName");
			fullName = fullName.replaceAll("[^A-Za-z]+$", "");
			String expiration_month = request.getParameter("expiration_month");
			expiration_month = expiration_month.replaceAll("[^0-9]", "");
			Cookie exp_month = new Cookie("month", expiration_month);
			String expiration_year = request.getParameter("expiration_year");
			expiration_year = expiration_year.replaceAll("[^0-9]", "");
			Cookie exp_year = new Cookie("year", expiration_year);
			Payment paymnt = pdao.createPayment(creditCardNumber, bankName, fullName, expiration_month,
					expiration_year);

			pdao.updateCustomer(customer_id, paymnt);

			Customer customer = pdao.searchCustomer(customer_id);
			Movie movie = (Movie) session.getAttribute("movie");

			tdao.bookTicket(customer, movie);
			int availSeats = movie.getAvailableSeats();
			System.out.println(availSeats+"avialbleseats");
			mdao.updateAvailableSeats(movie, availSeats, availSeats-1);			
			response.addCookie(exp_month);
			response.addCookie(exp_year);

		} catch (Exception e) {
			System.out.println( e.getMessage());
		}

		return "printTicket";
	}
}
