package org.csiro.igsn.open.controllers;


import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.csiro.igsn.entity.postgres.Prefix;
import org.csiro.igsn.entity.postgres.Registrant;
import org.csiro.igsn.entity.postgres.Resources;
import org.csiro.igsn.entity.service.PrefixEntityService;
import org.csiro.igsn.entity.service.RegistrantEntityService;
import org.csiro.igsn.entity.service.ResourceEntityService;
import org.csiro.igsn.exception.ExceptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/public/")
public class PublicCtrl {
	
	static final Logger log = Logger.getLogger(PublicCtrl.class);
	
	private ResourceEntityService resourceEntityService; 

	
	@Autowired
	public PublicCtrl(ResourceEntityService resourceEntityService){
		this.resourceEntityService = resourceEntityService;
	}
	

	
	@RequestMapping("getResource.do")
	public ResponseEntity<Object> getResource(
			@RequestParam(required = true, value ="resourceIdentifier") String resourceIdentifier,
			Principal user) {
		
		try {
			Resources r = this.resourceEntityService.searchResourceByIdentifier(resourceIdentifier);
			if(!r.getIsPublic() && user == null){
				return new ResponseEntity<Object>(new ExceptionWrapper("Login","Unauthorized Access") ,HttpStatus.UNAUTHORIZED);
			}else{
				return new ResponseEntity<Object>(r,HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Object>(new ExceptionWrapper("Fail to remove registrant",e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	

	
	
	
}
