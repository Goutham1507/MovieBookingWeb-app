package com.vgr.movie.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="customer_id")
	private long id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="email")
	private String email;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="phonenum")
	private String phonenum;
	
	@Column(name="address")
	private String address;
	
	@OneToOne
	MovieTicket movieTicket;
	
	
	public MovieTicket getMovieTicket() {
		return movieTicket;
	}

	public void setMovieTicket(MovieTicket movieTicket) {
		this.movieTicket = movieTicket;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="creditCardNumber")
	Payment payment;
	
	

	public Customer() {
	}
	
	public Customer(String firstName, String lastName, String gender, String email, String dob, String phonenum,
			String address) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dob = dob;
		this.phonenum = phonenum;
		this.address = address;
	}

	public Customer(MovieTicket movieTicket,String firstName, String lastName, String gender, String email, String dob, String phonenum,
			String address) {
		this.movieTicket=movieTicket;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.email = email;
		this.dob = dob;
		this.phonenum = phonenum;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
	

	

}
