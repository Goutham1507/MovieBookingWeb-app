package com.vgr.movie.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vgr.movie.dao.ListMoviesDAO;
import com.vgr.movie.dao.MovieDAO;
import com.vgr.movie.dao.MovieTicketDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Movie;

@Controller
@RequestMapping(value = "/*Movie*.htm")

public class MovieController {
	

	@Autowired
	MovieValidator validator;
	
	@Autowired
	ListMoviesDAO ldao;
	
	@Autowired
	MovieDAO mdao;
	
	@Autowired
	MovieTicketDAO tdao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		
	} 
	
	@RequestMapping(value = "/addMovies.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("movie") Movie movie,BindingResult result) throws Exception
	{
		
		validator.validate(movie, result);
    	if(result.hasErrors()){
    		return "addMovies"; 
    	}
    	
    	try
        {
    		long production_id = movie.getProduction_id(); 
    		String movieLocation=movie.getMovieLocation(); 
    		movieLocation = movieLocation.replaceAll("[^A-Za-z]+$", "");
    		String movieTime=movie.getMovieTime();
    		movieTime = movieTime.replaceAll("[^\\d:]", "");
    		int totalSeats=movie.getTotalSeats(); 
    		int amount=movie.getAmount();
    		String movieDate=movie.getMovieDate(); 
    		String movieName=movie.getMovieName();
    		int availableSeats=movie.getAvailableSeats();
    		
    		
            MovieDAO mdao = new MovieDAO();
            Movie movie1 = mdao.createMovie(production_id, movieLocation, totalSeats, amount, movieTime,  movieDate,
        			 movieName,  availableSeats);
        
            
        }
        catch (MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
    	
		return "addedMovie";
	}
	

	@RequestMapping(value = "/addMovies.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("movie") Movie movie)
	{
		return "addMovies";
	}
	
	@RequestMapping(value="/ListMovies.htm", method=RequestMethod.GET)
	public String listForm(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<String> listOfMovies = null;
		
		try{
			
			listOfMovies = ldao.listAllMovies();
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		session.setAttribute("movielist",listOfMovies);
		return "moviesList";
	}
	
	
	@RequestMapping(value="/updateMovies.htm", method=RequestMethod.GET)
	public String updateMovies(@ModelAttribute("movie") Movie movie, HttpServletRequest request) throws MovieException
	{
		String id = request.getParameter("id");
		long movie_id = Long.parseLong(id);
		
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession();
		
		if(action!=null){
		if(action.equalsIgnoreCase("update"))
		{
			Movie movie2 = mdao.searchMovieByID(movie_id);
		     request.setAttribute("movie2", movie2);
		   return "updateMovie";
		   
		}
		
		}
		
		
		
			return "ListMovies";
		
		
	}
	
	@RequestMapping(value="/deleteMovies.htm", method=RequestMethod.GET)
	public String deleteMovie(@ModelAttribute("movie") Movie movie, HttpServletRequest request) throws MovieException
	{
		try{
			String id = request.getParameter("id");
			
			long movie_id = Long.parseLong(id);
			
			HttpSession session = request.getSession();
			
			Movie movie2 = mdao.searchMovieByID(movie_id);			
			tdao.deleteTickets(movie_id);			
			mdao.deleteMovie(movie2);			
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		HttpSession session = request.getSession();
		List<String> listOfMovies = null;
		
		try{
			
			listOfMovies = ldao.listAllMovies();
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		session.setAttribute("movielist",listOfMovies);
		
		
		return "moviesList";
	}
	
	@RequestMapping(value="/updateMovie.htm", method=RequestMethod.POST)
	public String update(@ModelAttribute("movie") Movie movie,BindingResult result, HttpServletRequest request)
	{		
		try{
			
			mdao.updateMovie(movie);
			System.out.println("Now Seats available are"+movie.getAvailableSeats());
			System.out.println("movie saved/updated successfully");
			
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		HttpSession session = request.getSession();
		List<String> listOfMovies = null;
		
		try{
			
			listOfMovies = ldao.listAllMovies();
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		session.setAttribute("movielist",listOfMovies);
		
		return "moviesList";
	}

}
