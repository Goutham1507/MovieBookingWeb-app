package com.vgr.movie.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vgr.movie.pojo.Customer;

public class CustomerValidator implements Validator {

	@Override
	public boolean supports(Class aClass) {
		// TODO Auto-generated method stub
		return aClass.equals(Customer.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		
		Customer customer = (Customer)obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"firstName","error.invalid.firstName","First Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"lastName","error.invalid.lastName","Last Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"gender","error.invalid.gender","Gender Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"email","error.invalid.email","Email Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"dob","error.invalid.dob","Date of Birth Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"phonenum","error.invalid.phonenum","Phone Number Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"address","error.invalid.address","Address Required");
		
	}

}
