package com.vgr.movie.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Payment")
public class Payment {
	@Id
	@Column(name="creditCardNumber")
	private long creditCardNumber;
	@Column(name="bankName")
	private String bankName;
	@Column(name="exp_month")
	private String expiration_month;
	@Column(name="exp_year")
	private String expiration_year;
	@Column(name="fullName")
	private String fullName;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="creditCardNumber")
	Customer customer ;
	
	

	public Payment() {
	}
	
	
	
	public Payment(long creditCardNumber, String bankName, String expiration_month, String expiration_year,
			String fullName) {
		this.creditCardNumber = creditCardNumber;
		this.bankName = bankName;
		this.expiration_month = expiration_month;
		this.expiration_year = expiration_year;
		this.fullName = fullName;
	}



	public long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getExpiration_month() {
		return expiration_month;
	}

	public void setExpiration_month(String expiration_month) {
		this.expiration_month = expiration_month;
	}

	public String getExpiration_year() {
		return expiration_year;
	}

	public void setExpiration_year(String expiration_year) {
		this.expiration_year = expiration_year;
	}
	

}
