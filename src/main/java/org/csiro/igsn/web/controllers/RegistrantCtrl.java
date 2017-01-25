package org.csiro.igsn.web.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.csiro.igsn.entity.postgres.Prefix;
import org.csiro.igsn.entity.postgres.Registrant;
import org.csiro.igsn.entity.service.PrefixEntityService;
import org.csiro.igsn.entity.service.RegistrantEntityService;
import org.csiro.igsn.exception.ExceptionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping(value = "/web/")
public class RegistrantCtrl {
	
	static final Logger log = Logger.getLogger(RegistrantCtrl.class);
	
	private RegistrantEntityService registrantEntityService; 
	private PrefixEntityService prefixEntityService;
	
	@Autowired
	public RegistrantCtrl(RegistrantEntityService registrantEntityService, PrefixEntityService prefixEntityService){
		this.registrantEntityService = registrantEntityService;
		this.prefixEntityService = prefixEntityService;
	}
	
	@RequestMapping("listRegistrants.do")
	public ResponseEntity<List<Registrant>> listRegistrants(Principal user) {
		
		return new ResponseEntity<List<Registrant>>(this.registrantEntityService.listRegistrant(),HttpStatus.OK);
		
	}
	
	@RequestMapping("removeRegistrants.do")
	public ResponseEntity<Object> removeRegistrants(
			@RequestParam(required = true, value ="registrant") String registrant,
			Principal user) {
		
		try {
			this.registrantEntityService.removeRegistrant(registrant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Object>(new ExceptionWrapper("Fail to remove registrant",e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(this.registrantEntityService.listRegistrant(),HttpStatus.OK);
		
	}
	

	@RequestMapping("listPrefix.do")
	public ResponseEntity<Object> listPrefix(Principal user) {
		
		return new ResponseEntity<Object>(this.prefixEntityService.listAllPrefix(),HttpStatus.OK);
		
	}
	
	@RequestMapping("allocatePrefix.do")
	public ResponseEntity<Object> allocatePrefix(
			@RequestParam(required = true, value ="prefix") String prefix,
			@RequestParam(required = true, value ="registrant") String registrant,
			Principal user) throws Exception {
		this.registrantEntityService.allocatePrefix(prefix,registrant);
		return listPrefix(user);
	}
	
	
	@RequestMapping("unAllocatePrefix.do")
	public ResponseEntity<Object> unAllocatePrefix(
			@RequestParam(required = true, value ="prefix") String prefix,
			@RequestParam(required = true, value ="registrant") String registrant,
			Principal user){
		try {
			this.registrantEntityService.unAllocatePrefix(prefix,registrant);
		} catch (Exception e) {			
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.OK);
		}
		return listPrefix(user);
	}

}
