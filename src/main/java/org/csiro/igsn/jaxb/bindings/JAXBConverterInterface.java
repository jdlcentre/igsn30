package org.csiro.igsn.jaxb.bindings;


import java.util.List;

import org.csiro.igsn.entity.postgres.Resources;
import org.springframework.util.MultiValueMap;



public interface JAXBConverterInterface {

	public boolean supports(String metadataPrefix);
	
	public String getMetadataPrefix();
	
	public String getNamespace();
	
	public String getSchemaLocation();
	
	public Object convert(Resources resource);
	
	public Class getXMLRootClass();

	public Object objectFactoryParse(List<Resources> resources);
}
