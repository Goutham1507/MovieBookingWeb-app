package com.vgr.movie.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Users;

public class LoginDAO extends DAO {
	
	public boolean validateAdmin(String username,String password) throws MovieException
	{
		try{
			
			
			Query q = getSession().createQuery("from Users where userName=:username and password=:password and role='admin'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new MovieException(" user is not found", e);
		}finally{
			close();
		}
	
		
		
		return true;
		
	}
	
	
	public boolean validateUser(String username,String password) throws MovieException
	{
		try{
			
			
			SQLQuery q = getSession().createSQLQuery("select * from users where username=:username and password=:password and role='customer'");
			q.setString("username", username);
			q.setString("password",password);
		    Object obj = q.uniqueResult();
		    if(obj==null)
		    {
		    	return false;
		    }
			
			
		}
		catch(HibernateException e){
			rollback();
            throw new MovieException("user is not found", e);
		}finally{
			close();
		}
		return true;
		
	}
	
	
	
	
	public void addUser(String username, String password, String role) throws MovieException{
		
		try{
			begin();
			Users u = new Users(username, password, role);
			getSession().save(u);
			commit();
		}
		catch(HibernateException e){
			rollback();
            throw new MovieException(" user can't be added", e);
		}finally{
			close();
		}
	
		
		
	}
	
	public boolean userExists(String username)
	{
		try{
			begin();
			Query q = getSession().createQuery("From Users where username=:username");
			q.setString("username", username);
			List list = q.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}
			
			
			
		}
		catch(Exception e){
			
			System.out.println(e.getMessage());
		}
		finally{
			close();
		}
		return true;
	}

}
