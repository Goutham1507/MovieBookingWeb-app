package com.vgr.movie.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vgr.movie.dao.CustomerDAO;
import com.vgr.movie.dao.MovieTicketDAO;
import com.vgr.movie.pojo.Customer;
import com.vgr.movie.pojo.MovieTicket;


@Controller
@RequestMapping(value="/*customer*.htm")
public class CustomerController {

	@Autowired
	CustomerValidator validator;
	
	@Autowired
	CustomerDAO pdao;
	
	@Autowired
	MovieTicketDAO tdao;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	
	@RequestMapping(value = "/customer1.htm", method = RequestMethod.GET)
	public String checkUserInSession(HttpServletRequest request, @ModelAttribute("customer") Customer customer,BindingResult result ) {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		System.out.println(username);

		if (username == null) {
			return "message";
		}

		
			return "Customer";
		

	}

	
	@RequestMapping(value="/customer.htm", method=RequestMethod.GET)
	public String initialize(@ModelAttribute("customer")Customer customer,HttpServletRequest request,BindingResult result)
	{
		HttpSession session = request.getSession();
		int noOfTravellers=1;
		session.setAttribute("noOfTravellers",noOfTravellers);
		return "Customer";
	}
	
	
	@RequestMapping(value="/customer.htm", method=RequestMethod.POST)
	public String doSubmit(@ModelAttribute("customer")Customer customer,BindingResult result, HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		validator.validate(customer, result);
    	if(result.hasErrors()){
    		return "Customer"; 
    	}
    	
		try{
	
			String firstName=customer.getFirstName(); 
			firstName = firstName.replaceAll("[^A-Za-z]+$", "");
			String lastName=customer.getLastName(); 
			lastName = lastName.replaceAll("[^A-Za-z]+$", "");
			String gender=customer.getGender(); 
			String email=customer.getEmail(); 
			String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			         + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			String dob=customer.getDob(); 
			String phonenum=customer.getPhonenum();
			phonenum = phonenum.replaceAll("[^0-9]", "-");
			String address=customer.getAddress();
			
			Customer pas = pdao.createCustomer(firstName, lastName, gender, email, dob, phonenum, address);
			long customer_id = pas.getId(); 
			System.out.println("customer_id"+customer_id);
			session.setAttribute("customer_id", customer_id);
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return "payment";
	}
	
	@RequestMapping(value="/viewcustomers.htm", method=RequestMethod.GET)
	public String viewCustomer(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		List<MovieTicket> ticketList= new ArrayList<MovieTicket>();
		try{
			ticketList = tdao.TicketList();
			System.out.println(ticketList.size());
		   }
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		session.setAttribute("ticketList", ticketList);
        return "customerList";
	}
	
	
}

