package com.vgr.movie.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.vgr.movie.dao.CustomerDAO;
import com.vgr.movie.dao.MovieTicketDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Customer;
import com.vgr.movie.pojo.Movie;

@Controller
@RequestMapping(value = "/*Ticket.*")
public class TicketController {

	@Autowired
	CustomerDAO cdao;

	@Autowired
	MovieTicketDAO tdao;

	@RequestMapping(value = "/downloadTicket.pdf", method = RequestMethod.GET)
	public void downloadTicket(HttpServletRequest request, HttpServletResponse response) throws MovieException {
		HttpSession session = request.getSession();
		long customer_id = ((Long) session.getAttribute("customer_id"));
		Customer customer = cdao.searchCustomer(customer_id);
		Movie movie = (Movie) session.getAttribute("movie");

		try {

			response.setContentType("application/pdf");

			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
			Paragraph title = new Paragraph("Ticket Confirmation");
			Paragraph name = new Paragraph("Customer name:" + customer.getFirstName() + "" + customer.getLastName());
			Paragraph movies = new Paragraph(
					"Movie Name" + movie.getMovieName() + " movie Location " + movie.getMovieLocation());
			Paragraph movieDetails = new Paragraph(
					"movie Date" + movie.getMovieDate() + "Movie Time :" + movie.getMovieTime());

			document.add(title);
			document.add(name);
			document.add(movies);
			document.add(movieDetails);
			document.close();

			ServletOutputStream out = response.getOutputStream();
			baos.writeTo(out);
			out.flush();

		}

		catch (Exception e) {
			System.out.println("Could not add ticket object" + e.getMessage());
		}

	}

	@RequestMapping(value = "/emailTicket.htm", method = RequestMethod.GET)
	public String emailTicket(HttpServletRequest request, CustomerDAO cdao) throws MovieException {
		try {

			HttpSession session = request.getSession();
			long customer_id = ((Long) session.getAttribute("customer_id"));
			Customer customer = cdao.searchCustomer(customer_id);
			Movie movie = (Movie) session.getAttribute("movie");

			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("kasaiswagath95@gmail.com", ""));
			email.setSSLOnConnect(true);
			try {
				email.setFrom("kasaiswagath95@gmail.com");
				email.setSubject("Ticket Confirmation");
				email.setMsg("Hello,Customer:" + customer.getFirstName() + " " + customer.getLastName() + "\n"
						+ "Thank you for booking Ticket with us. Please find your movie details below " + "\n"
						+ "Movie Name" + movie.getMovieName() + " Movie location " + movie.getMovieLocation() + "\n"
						+ "Movie Date" + movie.getMovieDate() + "Movie Time :" + movie.getMovieTime() + "\n");
				email.addTo(customer.getEmail());
				email.send();
			} catch (EmailException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.out.println("Could not send email " + e.getMessage());
		}

		return "confirmation";
	}

	@RequestMapping(value = "/deleteTicket.htm", method = RequestMethod.GET)
	public String deleteTicket(HttpServletRequest request) throws MovieException {
		
			HttpSession session = request.getSession();
			long customer_id = ((Long) session.getAttribute("customer_id"));
			System.out.println(customer_id+"check");
			Customer customer = cdao.searchCustomer(customer_id);
			Movie movie = (Movie) session.getAttribute("movie");
			System.out.println(movie+"moviecheck");
			tdao.cancelTicket(customer, movie);

		

		return "confirmation";
	}
}
