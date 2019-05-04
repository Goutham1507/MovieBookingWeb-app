package com.vgr.movie.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vgr.movie.pojo.Production;

public class ProductionValidator implements Validator {

	@Override
	public boolean supports(Class pClass) {
		return pClass.equals(Production.class);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		// TODO Auto-generated method stub
		Production production =(Production) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"productionName","error.invalid.productionName","production Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"owner","error.invalid.owner","Owner Required");
		
		
	}



}
