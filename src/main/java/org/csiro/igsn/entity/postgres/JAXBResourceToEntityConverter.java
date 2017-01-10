package org.csiro.igsn.entity.postgres;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.csiro.igsn.jaxb.bindings.Resources.Resource;
import org.csiro.igsn.service.ControlledValueEntityService;


public class JAXBResourceToEntityConverter {
	
	ControlledValueEntityService controlledValueEntityService;
	private List<CvResourceType> cvResourceTypes;
	private List<CvMaterialTypes> cvMaterialTypes;
	private List<CvIdentifierType> cvIdentifierType;
	
	
	public JAXBResourceToEntityConverter(ControlledValueEntityService controlledValueEntityService){
		this.controlledValueEntityService = controlledValueEntityService;
		cvResourceTypes = this.controlledValueEntityService.listResourceType();
		cvMaterialTypes = this.controlledValueEntityService.listMaterialTypes();
		cvIdentifierType = this.controlledValueEntityService.listIdentifierType();
	}
	
	
	private CvResourceType searchResourceType(String resourceType){
		for(CvResourceType type : cvResourceTypes){
			if(type.getResourceType().equals(resourceType)){
				return type;
			}
		}
		return null;
	}
	
	
	
	
	public void convert(Resource resourceXML,Registrant registrant, Resources resourceEntity){
		
		resourceEntity.setRegisteredObjectType(resourceXML.getRegisteredObjectType());
		
		//VT: set alternateIdentifiers;
		Set<AlternateIdentifiers> alternateIdentifiers=new HashSet<AlternateIdentifiers>();
		for(String alternateIdentifier:resourceXML.getAlternateIdentifiers().getAlternateIdentifier()){
			alternateIdentifiers.add(new AlternateIdentifiers(resourceEntity,alternateIdentifier));
		}
		resourceEntity.setAlternateIdentifierses(alternateIdentifiers);
		
		//VT: landing page, public, resource title
		resourceEntity.setLandingPage(resourceXML.getLandingPage());
		resourceEntity.setIsPublic(Boolean.valueOf(resourceXML.getIsPublic().toString()));
		resourceEntity.setResourceTitle(resourceXML.getResourceTitle());
		
		//VT: resourceType
		Set<ResourceTypes> resourceTypeses = new HashSet<ResourceTypes>();
		for(String resourceType:resourceXML.getResourceTypes().getResourceType()){
			resourceTypeses.add(new ResourceTypes(resourceEntity,searchResourceType(resourceType)));
		}
		resourceEntity.setResourceTypeses(resourceTypeses);
		
		
		
	}
}
