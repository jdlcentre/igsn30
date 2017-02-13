package org.csiro.igsn.jaxb.bindings.registration;

import java.util.ArrayList;
import java.util.Date;

import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.Location;
import org.csiro.igsn.jaxb.bindings.registration.Resources.*;
import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.IsPublic;
import org.csiro.igsn.jaxb.bindings.registration.Resources.Resource.ResourceIdentifier;
import org.csiro.igsn.utilities.IGSNDateUtil;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
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
		resourceIdentifier.setValue(resourceJO.get("resourceIdentifier").getAsString());
		resourceXML.setResourceIdentifier(resourceIdentifier);
		
		resourceXML.setRegisteredObjectType(resourceJO.get("registeredObjectType").getAsString());
		
		resourceXML.setLandingPage(resourceJO.get("landingPage").getAsString());
		
		IsPublic isPublic = this.objectFactory.createResourcesResourceIsPublic();
		isPublic.setValue(resourceJO.get("isPublic").getAsBoolean());
		resourceXML.setIsPublic(isPublic);			
		
		resourceXML.setResourceTitle(resourceJO.get("resourceTitle").getAsString());
		
		
		resourceXML.setResourceTypes(this.objectFactory.createResourcesResourceResourceTypes());
		resourceXML.getResourceTypes().resourceType = new ArrayList<String>();
		JsonArray resourceTypeses = resourceJO.get("resourceTypeses").getAsJsonArray();
		for(JsonElement resourceType : resourceTypeses) {
			resourceXML.getResourceTypes().resourceType.add((resourceType.getAsJsonObject()).getAsJsonObject("cvResourceType").get("resourceType").getAsString());
		}
		
		
		resourceXML.setMaterialTypes(this.objectFactory.createResourcesResourceMaterialTypes());
		resourceXML.getMaterialTypes().materialType = new ArrayList<String>();
		JsonArray materialTypeses = resourceJO.get("materialTypeses").getAsJsonArray();
		for(JsonElement materialType : materialTypeses) {
			resourceXML.getMaterialTypes().materialType.add(materialType.getAsJsonObject().getAsJsonObject("cvMaterialTypes").get("materialType").getAsString());
		}
		
		
		resourceXML.setCurationDetails(this.objectFactory.createResourcesResourceCurationDetails());
		resourceXML.getCurationDetails().curation = new ArrayList<Resource.CurationDetails.Curation>();
		Resource.CurationDetails.Curation curationXML = new Resource.CurationDetails.Curation();
		
		JsonArray curationDetailses = resourceJO.get("curationDetailses").getAsJsonArray();
		for(JsonElement curationDetail : curationDetailses) {
			JsonObject curationDetailObject = curationDetail.getAsJsonObject();
			curationXML.setCurator(curationDetailObject.get("curator").getAsString());
			curationXML.setCurationDate(curationDetailObject.get("curationDate").getAsString().substring(0, 10));
			curationXML.setCurationLocation(curationDetailObject.get("curationLocation").getAsString());
			curationXML.setCuratingInstitution(this.objectFactory.createResourcesResourceCurationDetailsCurationCuratingInstitution());
			curationXML.getCuratingInstitution().setValue(curationDetailObject.get("curatingInstitution").getAsString());
			curationXML.getCuratingInstitution().setInstitutionURI(curationDetailObject.get("institutionUri").getAsString());
			resourceXML.getCurationDetails().curation.add(curationXML);
		}
		
		
		Location locationXML = this.objectFactory.createResourcesResourceLocation();
		locationXML.setLocality(this.objectFactory.createResourcesResourceLocationLocality());	
		if(resourceJO.get("location") !=null){
			JsonObject locationObject = resourceJO.get("location").getAsJsonObject();		
			locationXML.getLocality().setLocalityURI(locationObject.get("localityUri").getAsString());
			locationXML.getLocality().setValue(locationObject.get("locality").getAsString());
			locationXML.setGeometry(this.objectFactory.createResourcesResourceLocationGeometry());
			locationXML.getGeometry().setSrid(locationObject.get("srid").getAsString());
			locationXML.getGeometry().setVerticalDatum(locationObject.get("verticalDatum").getAsString());
			locationXML.getGeometry().setGeometryURI(locationObject.get("geometryUri").getAsString());
			locationXML.getGeometry().setValue(locationObject.get("wkt").getAsString());	
			resourceXML.setLocation(this.objectFactory.createResourcesResourceLocation(locationXML));
		}
		
		for(JsonElement materialType : materialTypeses) {
			resourceXML.getMaterialTypes().materialType.add(materialType.getAsJsonObject().getAsJsonObject("cvMaterialTypes").get("materialType").getAsString());
		}
		
		resourceXML.setAlternateIdentifiers(this.objectFactory.createResourcesResourceAlternateIdentifiers());
		resourceXML.getAlternateIdentifiers().alternateIdentifier = new ArrayList<String>();
		if(resourceJO.get("alternateIdentifierses")!=null){
			JsonArray alternateIdentifierses = resourceJO.get("alternateIdentifierses").getAsJsonArray();
			for(JsonElement alternateIdentifier : alternateIdentifierses) {		
				resourceXML.getAlternateIdentifiers().getAlternateIdentifier().add(alternateIdentifier.getAsJsonObject().get("alternateIdentifier").getAsString());
			}
		}
		
		if(resourceJO.get("classificationses")!=null){
			resourceXML.setClassifications(this.objectFactory.createResourcesResourceClassifications());
			resourceXML.getClassifications().classification = new ArrayList<Resource.Classifications.Classification>();
			JsonArray classificationses = resourceJO.get("classificationses").getAsJsonArray();
			for(JsonElement classification : classificationses) {		
				Resource.Classifications.Classification classificationXML = new Resource.Classifications.Classification();
				classificationXML.setValue(classification.getAsJsonObject().get("classification").getAsString());
				classificationXML.setClassificationURI(classification.getAsJsonObject().get("classificationUri").getAsString());
				resourceXML.getClassifications().classification.add(classificationXML);
			}
		}
		
		resourceXML.setPurpose(resourceJO.get("purpose").getAsString());
		
		if(resourceJO.get("sampledFeatureses")!=null){
			JsonArray sampledFeatureses = resourceJO.get("sampledFeatureses").getAsJsonArray();
			Resource.SampledFeatures sampledFeatures = this.objectFactory.createResourcesResourceSampledFeatures();	
			sampledFeatures.sampledFeature = new ArrayList<Resource.SampledFeatures.SampledFeature>();
			for(JsonElement sampledFeature : sampledFeatureses) {		
				JsonObject sampledFeatureObject = sampledFeature.getAsJsonObject();
				Resource.SampledFeatures.SampledFeature sampledFeatureXML = new Resource.SampledFeatures.SampledFeature();
				sampledFeatureXML.setValue(sampledFeatureObject.get("sampledFeature").getAsString());
				sampledFeatureXML.setSampledFeatureURI(sampledFeatureObject.get("sampledFeatureUri").getAsString());
				sampledFeatures.sampledFeature.add(sampledFeatureXML);
			}		
			resourceXML.setSampledFeatures(this.objectFactory.createResourcesResourceSampledFeatures(sampledFeatures));
		}
		
		Resource.Date date = new Resource.Date();
		date.setTimeInstant(resourceJO.get("resourceDate").getAsJsonObject().get("timeInstant").getAsString());
		resourceXML.setDate(this.objectFactory.createResourcesResourceDate(date));
		
		resourceXML.setMethod(this.objectFactory.createResourcesResourceMethod());
		resourceXML.getMethod().setValue(resourceJO.get("method").getAsJsonObject().get("method").getAsString());
		resourceXML.getMethod().setMethodURI(resourceJO.get("method").getAsJsonObject().get("methodUri").getAsString());
		
		resourceXML.setCampaign(resourceJO.get("campaign").getAsString());
		resourceXML.setComments(resourceJO.get("comments").getAsString());
		
		if(resourceJO.get("contributorses")!=null){
			resourceXML.setContributors(this.objectFactory.createResourcesResourceContributors());
			resourceXML.getContributors().contributor = new ArrayList<Resource.Contributors.Contributor>();
			JsonArray contributorses = resourceJO.get("contributorses").getAsJsonArray();
			for(JsonElement contributor : contributorses) {		
				JsonObject contributorObject = contributor.getAsJsonObject();
				Resource.Contributors.Contributor contributorXML = new Resource.Contributors.Contributor();
				contributorXML.setContributorName(contributorObject.get("contributorName").getAsString());
				contributorXML.setContributorIdentifier(this.objectFactory.createResourcesResourceContributorsContributorContributorIdentifier());
				contributorXML.getContributorIdentifier().setValue(contributorObject.get("contributorIdentifier").getAsString());
				contributorXML.getContributorIdentifier().setContributorIdentifierType(contributorObject.get("cvIdentifierType").getAsJsonObject().get("identifierType").getAsString());
				contributorXML.setContributorType(contributorObject.get("contributorType").getAsString());
				resourceXML.getContributors().contributor.add(contributorXML);
			}	
		}
		
		if(resourceJO.get("relatedResourceses")!=null){
			resourceXML.setRelatedResources(this.objectFactory.createResourcesResourceRelatedResources());
			resourceXML.getRelatedResources().relatedResource = new ArrayList<Resource.RelatedResources.RelatedResource>();
			JsonArray relatedResourceses = resourceJO.get("relatedResourceses").getAsJsonArray();
			for(JsonElement relatedResource : relatedResourceses) {		
				JsonObject relatedResourceObject = relatedResource.getAsJsonObject();
				Resource.RelatedResources.RelatedResource relatedResourceXML = new Resource.RelatedResources.RelatedResource();
				relatedResourceXML.setValue(relatedResourceObject.get("relatedResource").getAsString());
				relatedResourceXML.setRelationType(relatedResourceObject.get("relationType").getAsString());
				relatedResourceXML.setRelatedResourceIdentifierType(relatedResourceObject.get("cvIdentifierType").getAsJsonObject().get("identifierType").getAsString());
				resourceXML.getRelatedResources().relatedResource.add(relatedResourceXML);
			}
		}
		
		resourceXML.setLogDate(this.objectFactory.createResourcesResourceLogDate());
		resourceXML.getLogDate().setEventType(EventType.fromValue(resourceJO.get("eventType").getAsString()));
		resourceXML.getLogDate().setValue(IGSNDateUtil.getISODateFormatter().format(new Date()));
		
		return resourceXML;
	}



	

}
