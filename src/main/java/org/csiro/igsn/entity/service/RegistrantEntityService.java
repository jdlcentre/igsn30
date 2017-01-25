package org.csiro.igsn.entity.service;

import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.csiro.igsn.entity.postgres.Prefix;
import org.csiro.igsn.entity.postgres.Registrant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrantEntityService {
	
	PrefixEntityService prefixEntityService;
	
	@Autowired	
	public RegistrantEntityService(PrefixEntityService prefixEntityService){
		this.prefixEntityService = prefixEntityService;
	}
	
	
	public Registrant searchRegistrantAndPrefix(String user){
		EntityManager em = JPAEntityManager.createEntityManager();
		try{			
			Registrant registrant = em.createNamedQuery("Registrant.searchByUsernameJoinPrefix",Registrant.class)
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

	public boolean allocatePrefix(String prefix, String username) throws Exception {
		EntityManager em = JPAEntityManager.createEntityManager();
		try{	
			
			boolean allowed = false;
			Registrant registrantEntity = searchRegistrant(username);
			for(Prefix allocatorPrefix:registrantEntity.getAllocator().getPrefixes()){
				if(prefix.startsWith(allocatorPrefix.getPrefix().toUpperCase())){
					allowed = true;
				}
			}
			em.getTransaction().begin();	
			if(allowed){	
				Prefix prefixEntity = this.prefixEntityService.searchJoinRegistrant(prefix);
				prefixEntity.getRegistrants().add(registrantEntity);
				em.merge(prefixEntity);
				em.flush();
				em.getTransaction().commit();
				return true;
			}else{
				throw new Exception("Prefix is not allowed as it does not match allocator's prefixes.");
				
			}
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}finally{
			em.close();
		}
		
	}


	public boolean unAllocatePrefix(String prefix, String username) throws Exception {
		EntityManager em = JPAEntityManager.createEntityManager();
		try{	
			
			boolean allowed = false;
			Registrant registrantEntity = searchRegistrant(username);
			for(Prefix allocatorPrefix:registrantEntity.getAllocator().getPrefixes()){
				if(prefix.startsWith(allocatorPrefix.getPrefix().toUpperCase())){
					allowed = true;
				}
			}
			em.getTransaction().begin();	
			if(allowed){	
				Prefix prefixEntity = this.prefixEntityService.searchJoinRegistrant(prefix);
				
				for(Registrant r:prefixEntity.getRegistrants()){
					if(r.getRegistrantid()==registrantEntity.getRegistrantid()){
						prefixEntity.getRegistrants().remove(r);
						break;
					}
				}
				em.merge(prefixEntity);
				em.flush();
				em.getTransaction().commit();
				return true;
			}else{
				throw new Exception("Prefix is not allowed as it does not match allocator's prefixes.");				
			}
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}finally{
			em.close();
		}
		
	}


	public void removeRegistrant(String registrant) throws Exception {
		EntityManager em = JPAEntityManager.createEntityManager();
		try{	
			em.getTransaction().begin();
		
			Registrant registrantEntity = searchRegistrantAndPrefix(registrant);
			if(registrantEntity.getPrefixes()==null || registrantEntity.getPrefixes().isEmpty()){
				em.remove(em.contains(registrantEntity) ? registrantEntity : em.merge(registrantEntity));
				em.getTransaction().commit();
			}else{
				throw new Exception("Registrant cannot be deleted as it has prefixes assigned to it.");
			}
			
		
		}catch(Exception e){
			em.getTransaction().rollback();
			throw e;
		}finally{
			em.close();
		}
		
	}

	
	

	
}
