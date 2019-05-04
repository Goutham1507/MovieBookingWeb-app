package com.vgr.movie.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MovieTicket")
public class MovieTicket {
	
	@Id
	@GeneratedValue
	@Column(name="ticket_id")
	private long ticket_id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id")
	Customer customerDetails;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="movie_id")
	Movie movie;

	public MovieTicket() {
	}
	
	

	public MovieTicket(Customer customerDetails, Movie movie) {
		this.customerDetails = customerDetails;
		this.movie = movie;
	}



	public Customer getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}



	public long getTicket_id() {
		return ticket_id;
	}



	public void setTicket_id(long ticket_id) {
		this.ticket_id = ticket_id;
	}



	public Movie getMovie() {
		return movie;
	}



	public void setMovie(Movie movie) {
		this.movie = movie;
	}


	
	
	
}
