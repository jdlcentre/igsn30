package org.csiro.igsn.entity.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.csiro.igsn.entity.postgres.CvIdentifierType;
import org.csiro.igsn.entity.postgres.CvMaterialTypes;
import org.csiro.igsn.entity.postgres.CvResourceType;
import org.csiro.igsn.entity.postgres.Registrant;
import org.springframework.stereotype.Service;

@Service
public class RegistrantEntityService {
	
	public Registrant searchRegistrant(String user){
		EntityManager em = JPAEntityManager.createEntityManager();
		try{			
			Registrant registrant = em.createNamedQuery("Registrant.searchByUsername",Registrant.class)
				.setParameter("username", user)
				.getSingleResult();			 		 
			 return registrant;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			em.close();
		}
	}
	
	public List<Registrant> listRegistrant(){
		EntityManager em = JPAEntityManager.createEntityManager();
		try{			
			List<Registrant> registrant = em.createNamedQuery("Registrant.getAllRegistrant",Registrant.class)
					.getResultList();	 		 
			 return registrant;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			em.close();
		}
	}

	
	

	
}
