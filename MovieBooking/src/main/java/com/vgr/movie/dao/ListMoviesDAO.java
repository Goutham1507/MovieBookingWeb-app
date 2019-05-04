package com.vgr.movie.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.vgr.movie.exception.MovieException;

public class ListMoviesDAO  extends DAO{
	
	public List listMovies(String movieName,String movieDate)throws MovieException{
		
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from Movie where movieName =:movieName");
			q.setString("movieName", movieName);
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new MovieException("Could not find any Movies", e);
		}finally{
			close();
		}
		
		
	}
	
	public List listAllMovies()throws MovieException{
		
		List<String> list= null;
		
		try{
			begin();
			Query q = getSession().createQuery("from Movie");
			list = q.list();
			commit();
			return list;
		}
	
		catch(HibernateException e){
			rollback();
            throw new MovieException("Could not find any Movies", e);
		}
		finally{
			close();
		}
		
	}
	
	

}
