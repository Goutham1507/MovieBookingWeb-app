package com.vgr.movie.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Customer;
import com.vgr.movie.pojo.Movie;
import com.vgr.movie.pojo.MovieTicket;

public class MovieTicketDAO extends DAO {
	
public MovieTicket bookTicket(Customer customerDetails, Movie movieDetails) throws MovieException{
		
		
		try {
            begin();
            MovieTicket ticket = new MovieTicket(customerDetails, movieDetails);   
            getSession().save(ticket);
            commit();
            return ticket;
            
        } catch (HibernateException e) {
            rollback();
            throw new MovieException(" creating ticket: exception " + e.getMessage());
        }  finally{
			close();
		}      

		
		
	}
	
	public void cancelTicket(Customer customerDetails,Movie movieDetails) throws MovieException
	{
		try{
			begin();
			long customer_id = customerDetails.getId();
			long movie_id = movieDetails.getMovie_id();
			System.out.println(customer_id);
			System.out.println(movie_id);
			Query q = getSession().createQuery("delete from MovieTicket where customer_id=:customer_id and movie_id=:movie_id");
			
			q.setLong("customer_id",customer_id);
			q.setLong("movie_id",movie_id);
			int res=q.executeUpdate();
			if(res>0)
			{
			//Query q1= getSession().createSQLQuery("Select * From MovieTicket where customer_id="+customer_id+" and movie_id="+movie_id);
					
			//q.setLong("customer_id",customer_id);
			//q.setLong("movie_id",movie_id);
			//MovieTicket ticket=(MovieTicket)q.uniqueResult();
			//getSession().delete(ticket);
			
			
			int oldAvail = movieDetails.getAvailableSeats();
			movieDetails.setAvailableSeats(oldAvail+1);
			getSession().update(movieDetails);
			
			commit();
			}
		}
		catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create Movie", e);
            throw new MovieException("Exception while deleting ticket: " + e.getMessage());
        } 
		finally{
			close();
		}
	}
	
	public void deleteTickets(long movie_id) throws MovieException
	{
		try{
			begin();
			Query q = getSession().createQuery("From MovieTicket where movie_id=:movie_id");
			q.setLong("movie_id",movie_id);	
			List <MovieTicket>list = q.list();
			commit();
			
			for(MovieTicket t:list)
			{
				begin();
				getSession().delete(t);
				commit();
			}
			
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException("Exception while deleting ticket: " + e.getMessage());
        }   
		finally{
			close();
		}
	}
	
	public List TicketList()
	{
		List<MovieTicket>ticketList= new ArrayList<MovieTicket>();
		try{
		begin();
		Query q = getSession().createQuery("From MovieTicket");
		ticketList = q.list();
		commit();
		
		}
		catch (HibernateException e) {
            rollback();
            System.out.println("Exception while listing ticket: " + e.getMessage());
        }  
		finally{
			close();
		}
		return ticketList;
		
	}
	

}
