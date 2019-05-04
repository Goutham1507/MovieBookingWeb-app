package com.vgr.movie.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.vgr.movie.pojo.Movie;

public class MovieValidator implements Validator{

	@Override
	public boolean supports(Class<?> movie) {
		// TODO Auto-generated method stub
		return movie.equals(Movie.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		Movie movie = (Movie)target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"movieName","error.invalid.movieName","Movie Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"production_id","error.invalid.production_id","Production Id Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"movieLocation","error.invalid.movieLocation","movieLocation Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"movieTime","error.invalid.movieTime","movieTime Time Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"totalSeats","error.invalid.totalSeats","Total Seats Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"availableSeats","error.invalid.availableSeats","Available Seats Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"amount","error.invalid.amount","Amount Required");
		
		
	}
	

}
