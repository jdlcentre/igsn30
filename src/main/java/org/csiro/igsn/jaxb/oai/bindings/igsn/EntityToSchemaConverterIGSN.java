package org.csiro.igsn.jaxb.oai.bindings.igsn;

import java.util.ArrayList;
import java.util.List;
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
		
		return resourceXML;
	}



	

}
