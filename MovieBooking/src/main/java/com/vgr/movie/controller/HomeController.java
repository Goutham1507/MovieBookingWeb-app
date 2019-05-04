package com.vgr.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vgr.movie.dao.MovieDAO;
import com.vgr.movie.pojo.City;
import com.vgr.movie.pojo.Movie;




@Controller
@RequestMapping(value="/")
public class HomeController {
	
	@Autowired
	MovieDAO mdao;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(@ModelAttribute("movie")Movie movie) {
		
				return "index";
	}

	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String homePage(@ModelAttribute("movie")Movie movie) {
		
		return "index";
	}
	
	@RequestMapping(value = "/adminHome.htm", method = RequestMethod.GET)
	public String adminHomePage() {

		
		return "adminHome";
	}
	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String errorcheck() {

		
		return "error";
	}
	
	
}

