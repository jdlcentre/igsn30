package org.csiro.igsn.jaxb.oai.bindings.igsn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.csiro.igsn.entity.postgres.AlternateIdentifiers;
import org.csiro.igsn.entity.postgres.Classifications;
import org.csiro.igsn.entity.postgres.Contributors;
import org.csiro.igsn.entity.postgres.CurationDetails;
import org.csiro.igsn.entity.postgres.MaterialTypes;
import org.csiro.igsn.entity.postgres.RelatedResources;
import org.csiro.igsn.entity.postgres.ResourceTypes;
import org.csiro.igsn.entity.postgres.Resources;
import org.csiro.igsn.entity.postgres.SampledFeatures;
import org.csiro.igsn.jaxb.oai.bindings.JAXBConverterInterface;
import org.csiro.igsn.utilities.IGSNDateUtil;
import org.postgis.Geometry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class EntityToSchemaConverterIGSN implements JAXBConverterInterface{
	
	ObjectFactory objectFactory;
	final String METAPREFIX="igsn";
	final String NAMESPACE_FOR_BINDING="http://schema.igsn.org/description/1.0";
	final String SCHEMA_LOCATION_FOR_BINDING="https://raw.githubusercontent.com/IGSN/metadata/dev/description/resource.xsd";
	final Class XML_ROOT_CLASS = org.csiro.igsn.jaxb.oai.bindings.igsn.Resource.class;
	

	
	
	public EntityToSchemaConverterIGSN(){
		this.objectFactory = new ObjectFactory();		 
	}
	

	@Override
	public boolean supports(String metadataPrefix){
	
		if(metadataPrefix==null || !metadataPrefix.toLowerCase().equals(this.METAPREFIX)){
			return false;
		}else{
			return true;
		}
			
	}

	
	

	@Override
	public String getMetadataPrefix() {
		return METAPREFIX;
	}

	@Override
	public String getNamespace() {
		return NAMESPACE_FOR_BINDING;
	}

	@Override
	public String getSchemaLocation() {		
		return SCHEMA_LOCATION_FOR_BINDING;
	}
	
	@Override
	public Class getXMLRootClass() {		
		return XML_ROOT_CLASS;
	}

	@Override
	public org.csiro.igsn.jaxb.oai.bindings.igsn.Resource convert(Resources resource) {		
		Resource resourceXML = this.objectFactory.createResource();
		
		resourceXML.setRegistedObjectType(resource.getRegisteredObjectType());
		
		resourceXML.setIdentifier(this.objectFactory.createResourceIdentifier());
		resourceXML.getIdentifier().setValue(resource.getResourceIdentifier());
		resourceXML.getIdentifier().setType(IdentifierType.IGSN);
		
		
		
//		resourceXML.setLandingPage(resource.getLandingPage());
//		if(resource.getEmbargoEnd()!=null){
//			resourceXML.getIsPublic().setEmbargoEnd(IGSNDateUtil.getISODateFormatterShort().format(resource.getEmbargoEnd()));
//		}
		
		if(resource.getIsPublic()){
			resourceXML.setIsMetadataPublic(AccessType.PUBLIC);
		}else{
			resourceXML.setIsMetadataPublic(AccessType.PRIVATE);
		}
		
		resourceXML.setTitle(resource.getResourceTitle());
		
		if(resource.getAlternateIdentifierses()!=null && !resource.getAlternateIdentifierses().isEmpty()){			
			resourceXML.setAlternateIdentifiers(this.objectFactory.createResourceAlternateIdentifiers());
			resourceXML.getAlternateIdentifiers().alternateIdentifier = new ArrayList<Resource.AlternateIdentifiers.AlternateIdentifier>(); 
			for(AlternateIdentifiers alternateIdentifiers:resource.getAlternateIdentifierses()){
				Resource.AlternateIdentifiers.AlternateIdentifier alternateIdentiferXML = new Resource.AlternateIdentifiers.AlternateIdentifier();
				alternateIdentiferXML.setValue(alternateIdentifiers.getAlternateIdentifier());
				resourceXML.getAlternateIdentifiers().alternateIdentifier.add(alternateIdentiferXML);
			}
		}
		
		resourceXML.setResourceTypes(this.objectFactory.createResourceResourceTypes());
		
		for(ResourceTypes resourceTypes:resource.getResourceTypeses()){
			if(resourceXML.getResourceTypes().getResourceType().isEmpty()){
				resourceXML.getResourceTypes().setResourceType(mapResourceType(resourceTypes.getCvResourceType().getResourceType()));
			}else{
				if(resourceXML.getResourceTypes().alternateResourceTypes == null){
					Resource.ResourceTypes.AlternateResourceTypes alternateResourceTypeXML = new Resource.ResourceTypes.AlternateResourceTypes();
					alternateResourceTypeXML.alternateResourceType = new ArrayList<String>();
					resourceXML.getResourceTypes().setAlternateResourceTypes(alternateResourceTypeXML);
				}
				resourceXML.getResourceTypes().alternateResourceTypes.getAlternateResourceType().add(mapResourceType(resourceTypes.getCvResourceType().getResourceType()));
				
			}
		}
		
		resourceXML.setMaterials(this.objectFactory.createResourceMaterials());
		resourceXML.getMaterials().material = new ArrayList<MaterialType>();
		for(MaterialTypes materialType:resource.getMaterialTypeses()){			
			resourceXML.getMaterials().material.add(mapMaterialType(materialType.getCvMaterialTypes().getMaterialType()));
		}
		

		
		if(resource.getLocation()!=null){
			
			Resource.Locations locationXML = this.objectFactory.createResourceLocations();
			Resource.Locations.Geometry geometryXML = this.objectFactory.createResourceLocationsGeometry();
			geometryXML.setSridType("4326");
			String wkt = resource.getLocation().getWkt();
			geometryXML.setType(GeometryType.fromValueIgnoreCase(wkt.substring(0,wkt.indexOf('(')).trim()));
			geometryXML.setValue(wkt);
			locationXML.setGeometry(geometryXML);
			Resource.Locations.Toponym toponym = this.objectFactory.createResourceLocationsToponym();
			toponym.setName(resource.getLocation().getLocality());			
			toponym.setIdentifier(this.objectFactory.createResourceLocationsToponymIdentifier());
			toponym.getIdentifier().setValue(resource.getLocation().getLocalityUri());
			toponym.getIdentifier().setType(IdentifierType.URI);
			locationXML.setToponym(toponym);
			resourceXML.setLocations(locationXML);
		}
				
		if(resource.getResourceDate()!=null && !(resource.getResourceDate().getTimeInstant()==null && resource.getResourceDate().getTimePeriodStart()==null)){
			Resource.Date date = new Resource.Date();
			if(resource.getResourceDate().getTimeInstant()!=null && !resource.getResourceDate().getTimeInstant().isEmpty()){
				date.setTimeInstant(IGSNDateUtil.parseForGregorianCalendar(resource.getResourceDate().getTimeInstant()));
			}else{
				Resource.Date.TimePeriod timeperiod = new Resource.Date.TimePeriod();
				timeperiod.setStart(IGSNDateUtil.parseForGregorianCalendar(resource.getResourceDate().getTimePeriodStart()));
				timeperiod.setEnd(IGSNDateUtil.parseForGregorianCalendar(resource.getResourceDate().getTimePeriodEnd()));
				date.setTimePeriod(timeperiod);
			}			
			resourceXML.setDate(date);
		}
		
		
		
		
	
		
		
		resourceXML.setCurationDetails(this.objectFactory.createResourcesResourceCurationDetails());
		resourceXML.getCurationDetails().curation = new ArrayList<Resource.CurationDetails.Curation>();
		for(CurationDetails curationDetails:resource.getCurationDetailses()){
			Resource.CurationDetails.Curation curationDetailXML = new Resource.CurationDetails.Curation();
			curationDetailXML.setCurator(curationDetails.getCurator());
			curationDetailXML.setCurationDate(curationDetails.getCurationDate());
			curationDetailXML.setCurationLocation(curationDetails.getCurationLocation());
			curationDetailXML.setCuratingInstitution(this.objectFactory.createResourcesResourceCurationDetailsCurationCuratingInstitution());
			curationDetailXML.getCuratingInstitution().setInstitutionURI(curationDetails.getInstitutionUri());
			curationDetailXML.getCuratingInstitution().setValue(curationDetails.getCuratingInstitution());
			resourceXML.getCurationDetails().curation.add(curationDetailXML);
		}
		
		if(resource.getContributorses()!=null && !resource.getContributorses().isEmpty()){
			resourceXML.setContributors(this.objectFactory.createResourcesResourceContributors());
			resourceXML.getContributors().contributor = new ArrayList<Resource.Contributors.Contributor>();
			for(Contributors contributor:resource.getContributorses()){
				Resource.Contributors.Contributor contributorXML = new Resource.Contributors.Contributor();
				contributorXML.setContributorType(contributor.getContributorType());
				contributorXML.setContributorName(contributor.getContributorName());
				contributorXML.setContributorIdentifier(this.objectFactory.createResourcesResourceContributorsContributorContributorIdentifier());
				contributorXML.getContributorIdentifier().setContributorIdentifierType(contributor.getCvIdentifierType().getIdentifierType());
				contributorXML.getContributorIdentifier().setValue(contributor.getContributorIdentifier());
				resourceXML.getContributors().contributor.add(contributorXML);
			}
		}
		
		if(resource.getRelatedResourceses()!=null && !resource.getRelatedResourceses().isEmpty()){
			resourceXML.setRelatedResources(this.objectFactory.createResourcesResourceRelatedResources());
			resourceXML.getRelatedResources().relatedResource = new ArrayList<Resource.RelatedResources.RelatedResource>();
			for(RelatedResources relatedResources:resource.getRelatedResourceses()){
				Resource.RelatedResources.RelatedResource relatedResourcesXML = new Resource.RelatedResources.RelatedResource();
				relatedResourcesXML.setRelatedResourceIdentifierType(relatedResources.getCvIdentifierType().getIdentifierType());
				relatedResourcesXML.setRelationType(relatedResources.getRelationType());
				relatedResourcesXML.setValue(relatedResources.getRelatedResource());
				resourceXML.getRelatedResources().relatedResource.add(relatedResourcesXML);
			}
		}
		
		
		
		resourceXML.setLogDate(this.objectFactory.createResourcesResourceLogDate());
		resourceXML.getLogDate().setEventType(EventType.fromValue(resource.getLogDate().getEventType()));
		resourceXML.getLogDate().setValue(resource.getLogDate().getLogDate());
		
		
		return resourcesXML;
	}


	private MaterialType mapMaterialType(String materialType) {
		switch(materialType){
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/air": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_AIR;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/gas": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_GAS;			
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/ice": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_ICE;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/liquidAqueous": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_LIQUID_AQUEOUS;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/liquidOrganic": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_LIQUID_ORGANIC;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/mineral": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_MINERAL;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/organism": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_ORGANISM;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/other": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_OTHER;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/particulate": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_PARTICULATE;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/rock": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_ROCK;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/sediment": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_SEDIMENT;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/snow": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_SNOW;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/soil": return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_SOIL;
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/tissue": return MaterialType..HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_TISSUE;			
			default: return MaterialType.HTTP_VOCABULARY_ODM_2_ORG_MEDIUM_UNKNOWN;
		}
		
	}


	private String mapResourceType(String resourceType) {
		switch(resourceType){
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/automated": return "http://vocabulary.odm2.org/specimentype/automated";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/core" : return "http://vocabulary.odm2.org/specimentype/core";			
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreHalfRound" : return "http://vocabulary.odm2.org/specimentype/coreHalfRound";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/corePiece" : return "http://vocabulary.odm2.org/specimentype/corePiece";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreQuarterRound" : return "http://vocabulary.odm2.org/specimentype/coreQuarterRound";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSection": return "http://vocabulary.odm2.org/specimentype/coreSection";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSectionHalf": return "http://vocabulary.odm2.org/specimentype/coreSectionHalf";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreSub-Piece": return"http://vocabulary.odm2.org/specimentype/coreSub-Piece";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/coreWholeRound" : return "http://vocabulary.odm2.org/specimentype/coreWholeRound";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/cuttings" : return "http://vocabulary.odm2.org/specimentype/cuttings";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/dredge" : return "http://vocabulary.odm2.org/specimentype/dredge";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/foliageDigestion" : return "http://vocabulary.odm2.org/specimentype/foliageDigestion";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/foliageLeaching": return "http://vocabulary.odm2.org/specimentype/foliageLeaching";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/forestFloorDigestion" : return "http://vocabulary.odm2.org/specimentype/forestFloorDigestion";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/individualSample" : return "http://vocabulary.odm2.org/specimentype/individualSample";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/litterFallDigestion" : return "http://vocabulary.odm2.org/specimentype/litterFallDigestion";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/petriDishDryDeposition" : return "http://vocabulary.odm2.org/specimentype/petriDishDryDeposition";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/precipitationBulk" : return "http://vocabulary.odm2.org/specimentype/precipitationBulk";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/rockPowder" : return "http://vocabulary.odm2.org/specimentype/rockPowder";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/standardReferenceSpecimen" : return "http://vocabulary.odm2.org/specimentype/standardReferenceSpecimen";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/terrestrialSection" : return "http://vocabulary.odm2.org/specimentype/terrestrialSection";		
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/thinSection" : return "http://vocabulary.odm2.org/specimentype/thinSection";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/orientedCore" : return "http://vocabulary.odm2.org/specimentype/orientedCore";
			case "http://pid.geoscience.gov.au/def/voc/igsn-codelists/grab" : return "http://vocabulary.odm2.org/specimentype/grab";			
			default: return "http://vocabulary.odm2.org/specimentype/other";
		}
		
	}
	

}

