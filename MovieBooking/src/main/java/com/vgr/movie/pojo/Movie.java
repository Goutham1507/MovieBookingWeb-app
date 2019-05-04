package com.vgr.movie.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="movieDetails")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="movie_id")
	private long movie_id;
	
	public long getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(long movie_id) {
		this.movie_id = movie_id;
	}
	@Column(name="movieName")
	private String movieName ;
	
	@Column(name="production_id")
	private long production_id;
	
	@Column(name="movieLocation")
	private  String movieLocation;
	
	@Column(name="totalSeats")
	private int totalSeats;
	
	@Column(name="availableSeats")
	private int availableSeats;
	
	@Column(name="amount")
	private int amount;
	
	@Column(name="movieTime")
	private String movieTime;
	
	@Column(name="movieDate")
	private String movieDate;
	
	
	
	public Movie() {
	}

	public Movie(String movieName, long production_id, String movieLocation, int totalSeats, int availableSeats,
			int amount, String movieTime, String movieDate) {
		this.movieName = movieName;
		this.production_id = production_id;
		this.movieLocation = movieLocation;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.amount = amount;
		this.movieTime = movieTime;
		this.movieDate = movieDate;
	}
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public long getProduction_id() {
		return production_id;
	}
	public void setProduction_id(long production_id) {
		this.production_id = production_id;
	}

	public String getMovieLocation() {
		return movieLocation;
	}

	public void setMovieLocation(String movieLocation) {
		this.movieLocation = movieLocation;
	}

	public int getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getMovieTime() {
		return movieTime;
	}
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}
	public String getMovieDate() {
		return movieDate;
	}
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	
	

	
	

}
