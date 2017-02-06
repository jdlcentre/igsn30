package org.csiro.igsn.jaxb.bindings.registration;

import java.util.ArrayList;

import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.Location;
import org.csiro.igsn.jaxb.bindings.registration.Resources.*;
import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.IsPublic;
import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.ResourceIdentifier;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;


@Service
public class JsonToSchemaConverterCSIRO {
	
	ObjectFactory objectFactory;
	
	
	
	
	
	public JsonToSchemaConverterCSIRO(){
		this.objectFactory = new ObjectFactory();		 
	}
	


	public org.csiro.igsn.jaxb.bindings.registration.Resources.Resource convert(JsonElement resourceElement) {	
		
		JsonObject resourceJO = resourceElement.getAsJsonObject();
		
		Resource resourceXML = this.objectFactory.createResourcesResource();
		
		ResourceIdentifier resourceIdentifier = this.objectFactory.createResourcesResourceResourceIdentifier();
		resourceIdentifier.setValue(resourceJO.get("prefix").getAsString() + resourceJO.get("resourceIdentifier").getAsString());
		resourceXML.setResourceIdentifier(resourceIdentifier);
		
		resourceXML.setRegisteredObjectType(resourceJO.get("registeredObjectType").getAsString());
		
		resourceXML.setLandingPage(resourceJO.get("landingPage").getAsString());
		
		IsPublic isPublic = this.objectFactory.createResourcesResourceIsPublic();
		isPublic.setValue(resourceJO.get("isPublic").getAsBoolean());
		resourceXML.setIsPublic(isPublic);			
		
		resourceXML.setResourceTitle(resourceJO.get("resourceTitle").getAsString());
		
		
		resourceXML.setResourceTypes(this.objectFactory.createResourcesResourceResourceTypes());
		resourceXML.getResourceTypes().resourceType = new ArrayList<String>();
		resourceXML.getResourceTypes().resourceType.add(resourceJO.get("resourceType").getAsString());
		
		resourceXML.setMaterialTypes(this.objectFactory.createResourcesResourceMaterialTypes());
		resourceXML.getMaterialTypes().materialType = new ArrayList<String>();
		resourceXML.getMaterialTypes().materialType.add(resourceJO.get("materialType").getAsString());
		
		resourceXML.setCurationDetails(this.objectFactory.createResourcesResourceCurationDetails());
		resourceXML.getCurationDetails().curation = new ArrayList<Resource.CurationDetails.Curation>();
		Resource.CurationDetails.Curation curationXML = new Resource.CurationDetails.Curation();
		curationXML.setCurator(resourceJO.get("curator").getAsString());
		curationXML.setCurationDate(resourceJO.get("curationDate").getAsString().substring(0, 10));
		curationXML.setCurationLocation(resourceJO.get("curationLocation").getAsString());
		curationXML.setCuratingInstitution(this.objectFactory.createResourcesResourceCurationDetailsCurationCuratingInstitution());
		curationXML.getCuratingInstitution().setValue(resourceJO.get("curatingInstitution").getAsString());
		curationXML.getCuratingInstitution().setInstitutionURI(resourceJO.get("curationInstitutionUri").getAsString());
		resourceXML.getCurationDetails().curation.add(curationXML);
		
		
		Location locationXML = this.objectFactory.createResourcesResourceLocation();
		locationXML.setLocality(this.objectFactory.createResourcesResourceLocationLocality());
		locationXML.getLocality().setLocalityURI(resourceJO.get("localityUri").getAsString());
		locationXML.getLocality().setValue(resourceJO.get("locality").getAsString());
		locationXML.setGeometry(this.objectFactory.createResourcesResourceLocationGeometry());
		locationXML.getGeometry().setSrid(resourceJO.get("srid").getAsString());
		locationXML.getGeometry().setVerticalDatum(resourceJO.get("verticalDatum").getAsString());
		locationXML.getGeometry().setGeometryURI(resourceJO.get("geometryUri").getAsString());
		locationXML.getGeometry().setValue(resourceJO.get("wkt").getAsString());	
		resourceXML.setLocation(this.objectFactory.createResourcesResourceLocation(locationXML));
		
		resourceXML.setAlternateIdentifiers(this.objectFactory.createResourcesResourceAlternateIdentifiers());
		resourceXML.getAlternateIdentifiers().alternateIdentifier = new ArrayList<String>();
		resourceXML.getAlternateIdentifiers().getAlternateIdentifier().add(resourceJO.get("alternateIdentifier").getAsString());
		
		
		return resourceXML;
	}



	

}
