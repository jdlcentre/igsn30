package org.csiro.igsn.jaxb.bindings;


import org.csiro.igsn.entity.postgres.Resources;



public interface JAXBConverterInterface {

	public boolean supports(String metadataPrefix);
	
	public String getMetadataPrefix();
	
	public String getNamespace();
	
	public String getSchemaLocation();
	
	public Object convert(Resources resource);
	
	public Class getXMLRootClass();

}
