package com.vgr.movie.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="production")
public class Production {
	
	@Id
	@GeneratedValue
	@Column(name ="production_id")
	private long production_id;
	
	@Column(name="productionName")
	private String productionName;
	
	@Column(name="owner")
	 private String owner;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="production_id")
	 private Set<Movie> movies = new HashSet<Movie>();
	
	
	
	public Production() {
	}
	
	public Production(String productionName, String owner) {
		this.productionName = productionName;
		this.owner = owner;
	}
	public long getProduction_id() {
		return production_id;
	}
	public void setProduction_id(long production_id) {
		this.production_id = production_id;
	}
	public String getProductionName() {
		return productionName;
	}
	public void setProductionName(String productionName) {
		this.productionName = productionName;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Set<Movie> getMovies() {
		return movies;
	}
	public void setMovies(Set<Movie> movies) {
		this.movies = movies;
	}
	 
	 

}
