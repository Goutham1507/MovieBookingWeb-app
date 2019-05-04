package com.vgr.movie.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Customer;
import com.vgr.movie.pojo.Payment;

public class CustomerDAO extends DAO{
	
	public Customer createCustomer(String firstName, String lastName, String gender, String email, String dob, String phonenum,
			String address) throws MovieException
	{
		try{
			begin();
			Customer customer = new Customer(firstName, lastName, gender, email, dob, phonenum,
					 address);
			getSession().save(customer);
			commit();
			return customer;
		}
		
		catch (HibernateException e) {
            rollback();
            throw new MovieException( e.getMessage());
        }  
		finally{
			close();
		}
		
	}
	
	public Payment createPayment(long creditCardNumber, String bankName, String fullName, String expiration_month,
			String expiration_year)throws MovieException
	{
		try{
			begin();
			Payment p = new Payment(creditCardNumber, bankName, fullName, expiration_month,
					 expiration_year);
			getSession().save(p);
			commit();
			return p;
		}
		
		catch (HibernateException e) {
            rollback();
            throw new MovieException(" new payment: " + e.getMessage());
        }   
		finally{
			close();
		}
		
		
	}
	
	public void updateCustomer(long customer_id, Payment payment) throws MovieException
	{
		
		try{
			begin();
			Query query = getSession().createQuery("From Customer where customer_id =:customer_id ");
			query.setLong("customer_id", customer_id);
			Customer customer = (Customer)query.uniqueResult();
			customer.setPayment(payment);
			getSession().update(customer);
			commit();
			
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException(" updating customer: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public Customer searchCustomer(long customer_id) throws MovieException
	{
		Customer customer;
		try{
			begin();
			Query query = getSession().createQuery("From Customer where id=:customer_id ");
			query.setLong("customer_id", customer_id);
			System.out.println("customer_id"+customer_id+"inside dao");
			customer = (Customer) query.uniqueResult();			
			commit();
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException( e.getMessage());
        }  finally{
			close();
		}
		return customer;
	}
	
	public List ListCustomers() throws MovieException
	{
		try{
			begin();
			Query q = getSession().createQuery("From Customer");
			List list = q.list();
			commit();
			return list;
			
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException( e.getMessage());
        } 
		finally{
			close();
		}
		
	}

}
