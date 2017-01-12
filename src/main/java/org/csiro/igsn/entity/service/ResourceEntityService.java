package org.csiro.igsn.entity.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.csiro.igsn.entity.postgres.JAXBResourceToEntityConverter;
import org.csiro.igsn.entity.postgres.Registrant;
import org.csiro.igsn.entity.postgres.Resources;
import org.csiro.igsn.jaxb.bindings.EventType;
import org.csiro.igsn.jaxb.bindings.Resources.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResourceEntityService {
	
	JAXBResourceToEntityConverter jaxbResourceToEntityConverter;

	
	public static final int PAGING_SIZE=10;
	
	@Autowired
	public ResourceEntityService(ControlledValueEntityService controlledValueEntityService){
		this.jaxbResourceToEntityConverter = new JAXBResourceToEntityConverter(controlledValueEntityService);
	}

	


	public void insertResource(Resource resourceXml,Registrant registrant) throws Exception {
		
		EntityManager em = JPAEntityManager.createEntityManager();	
		Resources resourcesEntity = new Resources();
		try{
			em.getTransaction().begin();
			jaxbResourceToEntityConverter.convert(resourceXml,registrant,resourcesEntity);					
			em.persist(resourcesEntity);
			em.flush();
			em.getTransaction().commit();
		    em.close();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			throw e;
		}

	}
	
	
	public Resources searchResourceByIdentifier(String resourceIdentifier){
		EntityManager em = JPAEntityManager.createEntityManager();
		try{			
			Resources result = em.createNamedQuery("Resources.search",Resources.class)
		    .setParameter("resourceIdentifier", resourceIdentifier)
		    .getSingleResult();			 		
			 return result;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw e;
		}finally{
			em.close();	
		}
	}
	

	public void destroyResource(Resource resource) {
		EntityManager em = JPAEntityManager.createEntityManager();		
		try{
			em.getTransaction().begin();			
			Resources r = this.searchResourceByIdentifier(resource.getResourceIdentifier().getValue());
			r.getLogDate().setEventType((EventType.DESTROYED.value()));
			em.merge(r);
			em.flush();
			em.getTransaction().commit();		    
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			throw e;
		}finally{
			em.close();
		}
		
	}
	
	public void deprecateResource(Resource resource) {
		EntityManager em = JPAEntityManager.createEntityManager();		
		try{
			em.getTransaction().begin();			
			Resources r = this.searchResourceByIdentifier(resource.getResourceIdentifier().getValue());
			r.getLogDate().setEventType((EventType.DEPRECATED.value()));
			em.merge(r);
			em.flush();
			em.getTransaction().commit();		    
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			throw e;
		}finally{
			em.close();
		}
		
	}
	
	
	


	public void updateResource(Resource resourceXML,Registrant registrant) throws Exception {
		EntityManager em = JPAEntityManager.createEntityManager();
		Resources resourcesEntity = this.searchResourceByIdentifier(resourceXML.getResourceIdentifier().getValue());
		if(resourcesEntity==null){
			throw new Exception("Resource not found, unable to update: Change logElement event to register to add new record.");
		}
		try{
			em.getTransaction().begin();
			jaxbResourceToEntityConverter.convert(resourceXML,registrant,resourcesEntity);		
			resourcesEntity.getLogDate().setEventType((EventType.UPDATED.value()));
			em.merge(resourcesEntity);
			em.flush();
			em.getTransaction().commit();
		    em.close();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			throw e;
		}
		
	}

	public void testInsertResource(Resource resourceXML,Registrant registrant) throws Exception {
		
		if(!resourceXML.getLogDate().getEventType().equals(EventType.REGISTERED)){
			throw new Exception("You can only test insert with log event type = registered");
		}
		
		EntityManager em = JPAEntityManager.createEntityManager();
		Resources resourcesEntity = new Resources();
		try{
			em.getTransaction().begin();			
			jaxbResourceToEntityConverter.convert(resourceXML,registrant,resourcesEntity);	
			em.persist(resourcesEntity);
			em.flush();
			em.getTransaction().rollback();//VT: Because this is a test, it will always be rolled back
		    em.close();
		}catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
			em.close();
			throw e;
		}
	}

	public int getPagingSize() {
		// TODO Auto-generated method stub
		return this.PAGING_SIZE;
	}

	
	
	
	
	
}
