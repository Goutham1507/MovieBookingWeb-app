package com.vgr.movie.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.City;
import com.vgr.movie.pojo.Movie;

public class MovieDAO extends DAO{
	

	public Movie createMovie(long production_id,  String movieLocation, int totalSeats, int amount, String movieTime, String movieDate,
			String movieName, int availableSeats) throws MovieException
	{
		try{
		begin();	
		Movie movie = new Movie(movieName,production_id,movieLocation,totalSeats,availableSeats,amount, movieTime,  movieDate);
		getSession().save(movie);
		commit();
		System.out.println("Added movie and available seats are"+movie.getAvailableSeats());
		return movie;
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException(" new Movie Exception " + e.getMessage());
        }    finally{
			close();
		}    
	}
	
	public List listMovies() throws MovieException {
        try {
            begin();
            Query q = getSession().createQuery("from Movie");
            List list = q.list();
            commit();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not list the Movies", e);
        }finally{
			close();
		}
    }
	
	public List listCities(String cityname) throws MovieException
	{
		try{
			begin();
			Criteria city = getSession().createCriteria(City.class);
			city.add(Restrictions.ilike("cityname", cityname,MatchMode.ANYWHERE));
			List list = city.list();
			commit();
			return list;
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException(e.getMessage());
        }
		finally{
			close();
		}
	}
	
	public Movie searchMovieByID(long movie_id) throws MovieException {
        try {
        	
            begin();
            Query q = getSession().createQuery("from Movie where movie_id = :movie_id");
            q.setLong("movie_id", movie_id);
            Movie movie = (Movie) q.uniqueResult();
            System.out.println("DAO available seats"+movie.getAvailableSeats());
            System.out.println(movie.getMovieName());
            commit();
            return movie;
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not obtain the Movie details whose id is: " + movie_id + " " + e.getMessage());
        }
        finally{
			close();
		}
        
    }
	
	public void deleteMovie(Movie movie)
            throws MovieException {
        try {
            begin();
            getSession().delete(movie);
            commit();
            getSession().flush();
            getSession().clear();
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not delete movie", e);
        }finally{
			close();
		}
    }
	
	
	public void updateMovie(Movie movie) throws MovieException
	{
		try {
            begin();
            getSession().update(movie);
            commit();
            getSession().flush();
            getSession().clear();
            
        } catch (HibernateException e) {
            rollback();
            throw new MovieException(e.getMessage());
        }finally{
			close();
		}
	}
	
	public void updateAvailableSeats(Movie movie, int oldTotal, int newTotal) throws MovieException
	{
		try {
            begin();
            
            	int oldAvail = movie.getAvailableSeats();
				System.out.println("Old Seats available are"+movie.getAvailableSeats());
				movie.setAvailableSeats(newTotal-(oldTotal-oldAvail));
				System.out.println("Available seats are "+movie.getAvailableSeats());
				
			getSession().update(movie);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not update Movie", e);
        }finally{
			close();
		}
	}

}
