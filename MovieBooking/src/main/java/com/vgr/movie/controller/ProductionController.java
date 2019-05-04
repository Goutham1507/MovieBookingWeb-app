package com.vgr.movie.controller;

import javax.mail.Session;
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

import com.vgr.movie.dao.ProductionDAO;
import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Production;

@Controller
@RequestMapping(value = "/*Production.htm")
public class ProductionController {
	

	@Autowired
	ProductionValidator validator;
	
	@Autowired
	ProductionDAO pdao;
	
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	
	@RequestMapping(value = "/addProduction.htm", method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("production") Production production,BindingResult result) throws Exception
	{
		validator.validate(production,result);
		if(result.hasErrors()){
    		return "addProduction"; 
    	}
		
		try{
			
			String name= production.getProductionName();
			name = name.replaceAll("[^A-Za-z]+$", "");
			String owner = production.getOwner();
			owner = owner.replaceAll("[^A-Za-z]+$", "");
			
			pdao.create(name, owner);
			
		}
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
        }
        
    	
		return "addedProduction";

		
		
	}

	
	
	
	@RequestMapping(value = "/addProduction.htm", method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("production") Production production,BindingResult result)
	{
		
		return "addProduction";
	}
	
	@RequestMapping(value = "/deleteProduction.htm", method = RequestMethod.GET)
	public String delete()
	{
		
		return "deleteProduction";
	}
	
	@RequestMapping(value = "/deleteProduction.htm", method = RequestMethod.POST)
	public String deleteProduction(HttpServletRequest request)
	{
		int a=0;
		
		try{
			
			String id = request.getParameter("production_id");
			id = id.replaceAll("[^\\d]+$", "");
			
		    long production_id = Long.parseLong(id);		
		
		     a= pdao.deleteProduction(production_id);
		
		}
		
		catch(MovieException e)
        {
            System.out.println("Exception: " + e.getMessage());
            
        }
		HttpSession session = request.getSession();
			if(a==0)
			{
				request.setAttribute("delete", -1);				
				return "deleteProduction";
			}
        
			request.setAttribute("delete", 1);
		return "deletedProduction";
	
	}
	

}
