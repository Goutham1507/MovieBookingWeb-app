package com.vgr.movie.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vgr.movie.dao.MovieDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Movie;


@Controller
@RequestMapping(value="/*Cart.htm")
public class CartController {

	@Autowired
	
	MovieDAO mdao;
	
	@RequestMapping(value="/addToCart.htm", method=RequestMethod.GET)
	public String intialize(HttpServletRequest request, HttpServletResponse response) throws MovieException, IOException, JSONException{
		
		try{
		HttpSession session = request.getSession();
		System.out.println("Movie id" + request.getParameter("mid"));
		Long movieid = Long.parseLong(request.getParameter("mid"));
		System.out.println("Movie ID is"+movieid);
		List<Movie> cart;
 
		Movie mv = mdao.searchMovieByID(movieid);
		
		System.out.println("Cart Controller:"+mv.getAvailableSeats());
		
		int noOfSeats = mv.getAvailableSeats();
		PrintWriter out = response.getWriter();
		if(noOfSeats>0)
		{
			
			out.println("Seats are available");
			if (session.getAttribute("cart") != null) {
	             cart = (ArrayList<Movie>) session.getAttribute("cart");
	         } else {
	             cart = new ArrayList<Movie>();
	         }
			
			 cart.add(mv);
			 session.setAttribute("cart", cart);
			 session.setAttribute("movie", mv);
			 
			 float total = 0;
	         for (Movie f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
	         
	         return "viewCart";
	         
			}
		
		else
		{
			
			return "notAvailable";
			
		}	
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}

	}

	@RequestMapping(value="/removeFromCart.htm", method=RequestMethod.GET)
	public String removeItems(HttpServletRequest request) throws MovieException{
		
		HttpSession session = request.getSession();
		try{
			
			List<Movie> cart =(ArrayList<Movie>) session.getAttribute("cart");
			String id = request.getParameter("id");
			long movie_id = Long.parseLong(id); 
			
			
			for(Movie fd:cart){
				if(fd.getMovie_id()==movie_id){
					cart.remove(fd);
					break;
					
				}
			}
			
			session.setAttribute("cart", cart);
			
			 float total = 0;
	         for (Movie f : cart) {
	             total = total + f.getAmount();
	         }
	         
	         session.setAttribute("total", total);
		}
		
		catch(Exception e)
		{
			System.out.println( e);
		}
		
		
		return "viewCart";
	}
	
	@RequestMapping(value="/viewCart.htm", method=RequestMethod.GET)
	public String viewCart(HttpServletRequest request) {
		return "viewCart";
		
	}
		
	}

