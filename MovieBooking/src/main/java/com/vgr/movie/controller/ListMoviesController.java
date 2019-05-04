package com.vgr.movie.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vgr.movie.dao.ListMoviesDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Movie;


@Controller
@RequestMapping(value="/listMovi.htm")
public class ListMoviesController {

	@Autowired
	
	ListMoviesDAO ldao;
	
	
	@RequestMapping(value = "/listMovi.htm", method = RequestMethod.POST)
	public String initializeForm(@ModelAttribute("movie") Movie movie, HttpServletRequest request) throws MovieException
	{
		HttpSession session = request.getSession();
		String from = request.getParameter("movieName");
		String movieDate = request.getParameter("movieDate");

		System.out.println("From place"+from+"Movie date"+movieDate);
		
		try{
		
		List<String> movielist = ldao.listMovies(from, movieDate);
		
		int length = movielist.size();
		System.out.println("Length is "+length);
		
		
		session.setAttribute("movielist", movielist);
	
		
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
		
		return "listMovie";
	}
	
}

