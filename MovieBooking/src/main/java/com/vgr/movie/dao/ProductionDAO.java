package com.vgr.movie.dao;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.vgr.movie.exception.MovieException;
import com.vgr.movie.pojo.Production;





public class ProductionDAO extends DAO {

	public Production create(String productionName, String owner) throws MovieException{
		try {
			begin();
            Production production = new Production(productionName, owner);
            getSession().save(production);
            commit();
            return production;
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("create the Production exception", e);
        }
		finally{
			close();
		}
		
	}
	
	
	public void updateProduction(Production production) throws MovieException {
        try {
            begin();
            getSession().update(production);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not update the production", e);
        }
        finally{
			close();
		}
    }
	
	public Production searchProductionByID(long production_id) throws MovieException {
        try {
        	
            begin();
            Query q = getSession().createQuery("from Production where production_id = :production_id");
            q.setLong("production_id", production_id);
            Production production = (Production) q.uniqueResult();
            commit();
            return production;
        } catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not obtain the production details whose id is: " + production_id + " " + e.getMessage());
        }
        finally{
			close();
		}
    }
	

	
	public int deleteProduction(long production_id) throws MovieException{
		
		try{			
			Production production = searchProductionByID(production_id);
			if(production==null)
			{
			return 0;
			}
			begin();
			getSession().delete(production);
			commit();
			
			
		}
		catch (HibernateException e) {
            rollback();
            throw new MovieException("Could not delete the production", e);
            
        }
		finally{
			close();
		}
		return 1;
		
		
	}
	
	
}
